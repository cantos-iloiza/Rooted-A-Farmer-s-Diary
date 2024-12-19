package user;

import cropmanager.Crop;
import cropmanager.CropInfo;
import cropmanager.PlantedCrop;
import db.JDBCConnector;
import inventory.Inventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import miscellaneous.FarmerNotes;

public class FarmerUser extends User {
    private static List<FarmerUser> registeredUsers = new ArrayList<>();
    private ArrayList<PlantedCrop> plantedCrops = new ArrayList<>();
    private Inventory inventory = new Inventory();
    private FarmerNotes farmerNotes = new FarmerNotes();

    public static List<FarmerUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void setRegisteredUsers(List<FarmerUser> registeredUsers) {
        FarmerUser.registeredUsers = registeredUsers;
    }

    public FarmerUser(String username, String password) {
        super(username, password);
    }

    public static FarmerUser authenticate(String username, String password) {
        try (Connection conn = JDBCConnector.getConnection()) {
            String query = "SELECT * FROM farmer_users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password); 
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new FarmerUser(username, password); 
                }
            }
        } 
        catch (SQLException e) {
            System.out.println("Error during authentication.");
            e.printStackTrace();
        }
        return null; 
    }

    public boolean register() {
        for (FarmerUser user : registeredUsers) {
            if (user.getUsername().equals(this.username)) {
                return false;
            }
        }
        registeredUsers.add(this);
        return true;
    }

    public void loadPlantedCrops() {
        plantedCrops.clear();
        try (Connection conn = JDBCConnector.getConnection()) {
            String query = "SELECT crop_name, planting_date FROM planted_crops WHERE farmer_username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, this.username);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String cropName = rs.getString("crop_name");
                    LocalDate plantingDate = rs.getDate("planting_date").toLocalDate();
                    Crop crop = CropInfo.getInstance().getCrops().stream()
                            .filter(c -> c.getName().equals(cropName))
                            .findFirst()
                            .orElse(null);
                    if (crop != null) {
                        plantedCrops.add(new PlantedCrop(crop, plantingDate));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading planted crops for " + username);
        }
    }

    public void savePlantedCrops() {
        try (Connection conn = JDBCConnector.getConnection()) {
            String deleteQuery = "DELETE FROM planted_crops WHERE farmer_username = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setString(1, this.username);
                deleteStmt.executeUpdate();
            }

            String insertQuery = "INSERT INTO planted_crops (farmer_username, crop_name, planting_date) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                for (PlantedCrop plantedCrop : plantedCrops) {
                    insertStmt.setString(1, this.username);
                    insertStmt.setString(2, plantedCrop.getCrop().getName());
                    insertStmt.setDate(3, java.sql.Date.valueOf(plantedCrop.getPlantingDate()));
                    insertStmt.executeUpdate();
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving planted crops for " + username);
        }
    }

    public ArrayList<PlantedCrop> getPlantedCrops() {
        return plantedCrops;
    }

    public void setPlantedCrops(ArrayList<PlantedCrop> plantedCrops) {
        this.plantedCrops = plantedCrops;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public FarmerNotes getFarmerNotes() {
        return farmerNotes;
    }

    public void setFarmerNotes(FarmerNotes farmerNotes) {
        this.farmerNotes = farmerNotes;
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}