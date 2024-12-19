package cropmanager;

import db.JDBCConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class CropInfo {
    private static CropInfo instance;
    private ArrayList<Crop> crops;

    private CropInfo() {
        crops = new ArrayList<>();
        loadCropsFromDatabase();
    }

    public static CropInfo getInstance() {
        if (instance == null) {
            instance = new CropInfo();
        }
        return instance;
    }

    private void loadCropsFromDatabase() {
        try (Connection conn = JDBCConnector.getConnection()) {
            String query = "SELECT * FROM crops";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                int seasonStart = rs.getInt("season_start");
                int seasonEnd = rs.getInt("season_end");
                int wateringSchedule = rs.getInt("watering_schedule");
    
                HashMap<String, Integer> growthStages = getGrowthStages(conn, rs.getInt("id"));
                HashMap<String, Integer> fertilizerSchedule = getFertilizerSchedule(conn, rs.getInt("id"));
    
                Crop crop = new Crop(name, type, seasonStart, seasonEnd, wateringSchedule,
                                     growthStages, sortFertilizerSchedule(fertilizerSchedule), null);
                crops.add(crop);
            }
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading crops from database.");
        }
    }    
    
    private HashMap<String, Integer> sortFertilizerSchedule(HashMap<String, Integer> fertilizerSchedule) {
        HashMap<String, Integer> sortedSchedule = new HashMap<>();
        sortedSchedule.put("First Application", fertilizerSchedule.get("First Application"));
        sortedSchedule.put("Second Application", fertilizerSchedule.get("Second Application"));
        sortedSchedule.put("Third Application", fertilizerSchedule.get("Third Application"));
        return sortedSchedule;
    }    

    public void initializeCrops() {
        crops.clear();
        loadCropsFromDatabase();
        System.out.println("Crops initialized.");
    }

    public void viewPlantWiki(Scanner scanner) {
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>      Available Crops:      <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");

        int index = 1;
        for (Crop crop : this.crops) {
            System.out.println(index + ". " + crop.getName());
            index++;
        }

        System.out.print("Enter the index of the crop to view: ");
        int cropIndex = scanner.nextInt();
        scanner.nextLine();

        if (cropIndex < 1 || cropIndex > this.crops.size()) {
            System.out.println("Invalid crop index. Returning to menu.");
            return;
        }

        Crop selectedCrop = this.crops.get(cropIndex - 1);

        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>      Crop Details:      <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("Crop Name: " + selectedCrop.getName());
        System.out.println("Type: " + selectedCrop.getType());
        System.out.println("Season: " + selectedCrop.getSeasonStart() + " to " + selectedCrop.getSeasonEnd());
        System.out.println("Watering Schedule: Every " + selectedCrop.getWateringSchedule() + " days");

        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>   Fertilizer Schedule:  <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        selectedCrop.getFertilizerSchedule().entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEach(entry -> {
                System.out.println("  - " + entry.getKey() + ": Day " + entry.getValue());
            });

        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>      Growth Stages:     <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        selectedCrop.getGrowthStages().entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEach(entry -> {
                System.out.println("  - " + entry.getKey() + ": Day " + entry.getValue());
            });
    }

    
    private HashMap<String, Integer> getGrowthStages(Connection conn, int cropId) throws SQLException {
        HashMap<String, Integer> stages = new HashMap<>();
        String query = "SELECT * FROM crop_growth_stages WHERE crop_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, cropId);
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            stages.put("Sprouting", rs.getInt("sprouting_day"));
            stages.put("Seeding", rs.getInt("seeding_day"));
            stages.put("Budding", rs.getInt("budding_day"));
            stages.put("Flowering", rs.getInt("flowering_day"));
            stages.put("Harvest", rs.getInt("harvest_day"));
        } else {
            System.out.println("No growth stages found for crop ID: " + cropId);
        }
    
        return stages;
    }       

    private HashMap<String, Integer> getFertilizerSchedule(Connection conn, int cropId) throws SQLException {
        HashMap<String, Integer> fertilizerSchedule = new HashMap<>();
        String query = "SELECT * FROM crop_fertilizer_schedule WHERE crop_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, cropId);
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            fertilizerSchedule.put("First Application", rs.getInt("first_application_day"));
            fertilizerSchedule.put("Second Application", rs.getInt("second_application_day"));
            fertilizerSchedule.put("Third Application", rs.getInt("third_application_day"));
        } 
        
        else {
            System.out.println("No fertilizer schedule found for crop ID: " + cropId);
            fertilizerSchedule.put("First Application", null);
            fertilizerSchedule.put("Second Application", null);
            fertilizerSchedule.put("Third Application", null);
        }
    
        return fertilizerSchedule;
    }    

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public void addCrop(Crop crop) {
        crops.add(crop);
    }

    public void setCrops(ArrayList<Crop> crops) {
        this.crops = crops;
    }
}
