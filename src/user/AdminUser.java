package user;

import cropmanager.CropInfo;
import db.JDBCConnector;
import inventory.Inventory;
import inventory.InventoryManager;
import java.sql.*;
import java.util.Scanner;

public class AdminUser extends User {
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "password";

    private CropInfo cropInfo;
    private Inventory inventory;

    public AdminUser(CropInfo cropInfo, Inventory inventory) {
        super(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        this.cropInfo = cropInfo;
        this.inventory = inventory;
    }

    public void addNewCrop(Scanner scanner) {
        System.out.print("Enter crop name: ");
        String cropName = scanner.nextLine();
        System.out.print("Enter crop type: ");
        String cropType = scanner.nextLine();
        int seasonStart = getValidatedMonth(scanner, "Enter season start (MM): ");
        int seasonEnd = getValidatedMonth(scanner, "Enter season end (MM): ");
        int wateringSchedule = getValidatedInt(scanner, "Enter watering schedule (days): ");
    
        try (Connection conn = JDBCConnector.getConnection()) {
            String insertCropQuery = "INSERT INTO crops (name, type, season_start, season_end, watering_schedule) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCropQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, cropName);
                stmt.setString(2, cropType);
                stmt.setInt(3, seasonStart);
                stmt.setInt(4, seasonEnd);
                stmt.setInt(5, wateringSchedule);
                stmt.executeUpdate();
    
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int cropId = rs.getInt(1);
                        addGrowthStages(scanner, conn, cropId);
                        addFertilizerSchedule(scanner, conn, cropId);
    
                        addSeedToInventory(conn, cropName);
    
                        cropInfo.initializeCrops();
    
                        System.out.println("Crop successfully added: " + cropName);
                    } 
                    else {
                        System.out.println("Failed to add crop. Please try again.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding crop to the database: " + e.getMessage());
        }
    }
    
    private void addSeedToInventory(Connection conn, String cropName) throws SQLException {
        String checkSeedQuery = "SELECT * FROM inventory WHERE item_name = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSeedQuery)) {
            checkStmt.setString(1, cropName + " seeds"); 
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) { 
                String insertInventoryQuery = "INSERT INTO inventory (item_name, quantity) VALUES (?, 10)";
                try (PreparedStatement stmt = conn.prepareStatement(insertInventoryQuery)) {
                    stmt.setString(1, cropName + " seeds"); 
                    stmt.executeUpdate();
                    System.out.println("Seed for " + cropName + " added to inventory with quantity 10.");
                }
            } 
            else {
                System.out.println("Seed for " + cropName + " already exists in inventory.");
            }
        }
    }     

    private void addGrowthStages(Scanner scanner, Connection conn, int cropId) throws SQLException {
        String query = "INSERT INTO crop_growth_stages (crop_id, sprouting_day, seeding_day, budding_day, flowering_day, harvest_day) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cropId);
            stmt.setInt(2, getValidatedInt(scanner, "Enter Sprouting day: "));
            stmt.setInt(3, getValidatedInt(scanner, "Enter Seeding day: "));
            stmt.setInt(4, getValidatedInt(scanner, "Enter Budding day: "));
            stmt.setInt(5, getValidatedInt(scanner, "Enter Flowering day: "));
            stmt.setInt(6, getValidatedInt(scanner, "Enter Harvest day: "));
            stmt.executeUpdate();
        }
    }

    private void addFertilizerSchedule(Scanner scanner, Connection conn, int cropId) throws SQLException {
        String query = "INSERT INTO crop_fertilizer_schedule (crop_id, first_application_day, second_application_day, third_application_day) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cropId);
            stmt.setInt(2, getValidatedInt(scanner, "Enter first application day: "));
            stmt.setInt(3, getValidatedInt(scanner, "Enter second application day: "));
            stmt.setInt(4, getValidatedInt(scanner, "Enter third application day: "));
            stmt.executeUpdate();
        }
    }

    public void manageAdminInventory(Scanner scanner, InventoryManager inventoryManager) {
        boolean inventoryMenuActive = true;
        while (inventoryMenuActive) {
            System.out.println("\n\n Welcome to Inventory, Admin!");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.println("1. View Inventory");
            System.out.println("2. Update Inventory (Add/Restock Items)");
            System.out.println("3. Return to Admin Menu");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.print("Choose an option: ");

            int inventoryChoice = getValidatedInt(scanner, "");
            switch (inventoryChoice) {
                case 1:
                    inventoryManager.displayInventoryContents();
                    break;
                case 2:
                    System.out.print("Enter item name to add or restock: ");
                    String itemName = scanner.nextLine();
                    int quantityToAdd = getValidatedInt(scanner, "Enter quantity to add: ");
                    inventoryManager.restockItem(itemName, quantityToAdd);
                    System.out.println("Item '" + itemName + "' updated with quantity " + quantityToAdd);
                    break;
                case 3:
                    inventoryMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private int getValidatedInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private int getValidatedMonth(Scanner scanner, String prompt) {
        while (true) {
            int month = getValidatedInt(scanner, prompt);
            if (month >= 1 && month <= 12) {
                return month;
            } 
            else {
                System.out.println("Invalid month. Please enter a number between 1 and 12.");
            }
        }
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public CropInfo getCropInfo() {
        return cropInfo;
    }

    public void setCropInfo(CropInfo cropInfo) {
        this.cropInfo = cropInfo;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
