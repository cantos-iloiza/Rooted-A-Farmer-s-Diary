import cropmanager.CropInfo;
import cropmanager.CropManagement;
import cropmanager.PlantedCrop;
import inventory.Inventory;
import inventory.InventoryManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import user.AdminUser;
import user.FarmerUser;

public class Main {
    private static ArrayList<PlantedCrop> plantedCrops = new ArrayList<>();
    private static LocalDate userDate;

    public static void main(String[] args) {
        CropInfo cropInfo = CropInfo.getInstance();
        Inventory inventory = new Inventory();
        InventoryManager inventoryManager = new InventoryManager(inventory);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nWelcome to Rooted: A Farmer's Diary!");
            userDate = initializeDate(scanner);

            System.out.println("Press Enter to Start!");
            scanner.nextLine();

            boolean appRunning = true;

            while (appRunning) {
                System.out.println("\n\n\n1. Be a Farmer");
                System.out.println("2. Be the Admin");
                System.out.println("3. Exit");
                System.out.print("Today, I choose to: ");

                int option = getValidatedChoice(scanner);
                switch (option) {
                    case 1:
                        handleFarmerOption(scanner);
                        break;
                    case 2:
                        handleAdminOption(scanner, cropInfo, inventoryManager);
                        break;
                    case 3:
                        System.out.println("Exiting Rooted. Goodbye!");
                        appRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    private static void handleFarmerOption(Scanner scanner) {
        boolean farmerMenuActive = true;
        while (farmerMenuActive) {
            System.out.println("\nFarmer Menu:");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Return");
            System.out.print("Choose an option: ");

            int farmerChoice = getValidatedChoice(scanner);
            switch (farmerChoice) {
                case 1:
                    FarmerUser farmer = handleFarmerLogin(scanner);
                    if (farmer != null) {
                        handleFarmerSession(scanner, farmer);
                    }
                    break;
                case 2:
                    handleFarmerSignup(scanner);
                    break;
                case 3:
                    farmerMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void handleAdminOption(Scanner scanner, CropInfo cropInfo, InventoryManager inventoryManager) {
        AdminUser admin = handleAdminLogin(scanner);
        if (admin != null) {
            handleAdminSession(admin, cropInfo, inventoryManager, scanner);
        }
    }

    private static void handleFarmerSession(Scanner scanner, FarmerUser user) {
        CropInfo cropInfo = CropInfo.getInstance();
        cropInfo.initializeCrops();
        CropManagement cropManagement = new CropManagement(cropInfo, user.getInventory());

        boolean userLoggedIn = true;
        while (userLoggedIn) {
            System.out.println("\n\n\n\n\nWelcome to Rooted, Farmer " + user.getUsername() + "!");
            System.out.println("Today is " + userDate);
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.println("Main Menu:");
            System.out.println("1. Crop Management");
            System.out.println("2. PlantWiki");
            System.out.println("3. Farmer's Notes");
            System.out.println("4. Inventory");
            System.out.println("5. Log Out");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.print("Enter your choice: ");

            int choice = getValidatedChoice(scanner);
            switch (choice) {
                case 1:
                    cropManagement.manageCrops(scanner, user.getPlantedCrops());
                    break;
                case 2:
                    cropInfo.viewPlantWiki(scanner);
                    break;
                case 3:
                    user.getFarmerNotes().manageNotes(scanner);
                    break;
                case 4:
                    InventoryManager inventoryManager = new InventoryManager(user.getInventory());
                    inventoryManager.manageInventory(scanner);
                    break;
                case 5:
                    System.out.println("Time to hit the haystack and recharge--your farm awaits your return!");
                    userLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void handleAdminSession(AdminUser adminUser, CropInfo cropInfo, InventoryManager inventoryManager, Scanner scanner) {
        cropInfo.initializeCrops();

        boolean adminLoggedIn = true;
        while (adminLoggedIn) {
            System.out.println("\n\n\n\n\nWelcome back, Admin!");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.println("Admin Menu:");
            System.out.println("1. Add New Crop");
            System.out.println("2. View PlantWiki");
            System.out.println("3. Manage Inventory");
            System.out.println("4. Log Out");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.print("Enter your choice: ");

            int choice = getValidatedChoice(scanner);
            switch (choice) {
                case 1:
                    adminUser.addNewCrop(cropInfo, inventoryManager, scanner);
                    break;
                case 2:
                    cropInfo.viewPlantWiki(scanner);
                    break;
                case 3:
                    adminUser.manageAdminInventory(scanner, inventoryManager);
                    break;
                case 4:
                    System.out.println("Admin logged out.");
                    adminLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static LocalDate initializeDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter today's date (MM-dd-yyyy): ");
            String dateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

            try {
                return LocalDate.parse(dateInput, formatter);
            } 
            
            catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    private static int getValidatedChoice(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } 
            
            catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static FarmerUser handleFarmerLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        FarmerUser user = FarmerUser.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome back, Farmer " + username + "!");
        } 
        
        else {
            System.out.println("Login failed. Please try again or choose to Sign Up.");
        }
        return user;
    }

    private static AdminUser handleAdminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        AdminUser admin = new AdminUser();
        if (admin.login(username, password)) {
            System.out.println("Admin login successful!");
            return admin;
        } 
        
        else {
            System.out.println("Invalid admin credentials.");
            return null;
        }
    }

    private static FarmerUser handleFarmerSignup(Scanner scanner) {
        System.out.print("Enter username for new account: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        FarmerUser newUser = new FarmerUser(username, password);
        if (newUser.register()) {
            System.out.println("Registration successful! Welcome, Farmer " + username + "!");
            return newUser;
        } 
        
        else {
            System.out.println("Registration failed. Username already exists. Please try again.");
            return null;
        }
    }

    public static ArrayList<PlantedCrop> getPlantedCrops() {
        return plantedCrops;
    }

    public static void setPlantedCrops(ArrayList<PlantedCrop> plantedCrops) {
        Main.plantedCrops = plantedCrops;
    }
}