package cropmanager;

import db.JDBCConnector;
import inventory.Inventory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CropManagement {
    private ArrayList<PlantedCrop> plantedCrops;
    private CropInfo cropInfo;
    private Inventory inventory;

    public CropManagement(CropInfo cropInfo, Inventory inventory) {
        this.cropInfo = cropInfo;
        this.inventory = inventory;
        this.plantedCrops = new ArrayList<>();
    }

    public void manageCrops(Scanner scanner, ArrayList<PlantedCrop> userCrops) {
        this.plantedCrops = userCrops;

        while (true) {
            System.out.println("\n\n\n\nWelcome to Crop Management, Farmer!");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.println("1. Plant a crop");
            System.out.println("2. View watering schedule");
            System.out.println("3. View fertilizer schedule");
            System.out.println("4. View crop growth");
            System.out.println("5. Return to main menu");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
            System.out.print("Enter your choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1:
                    plantCrop(scanner);
                    break;
                case 2:
                    displayWateringSchedule();
                    break;
                case 3:
                    displayFertilizerSchedule();
                    break;
                case 4:
                    viewCropGrowth();
                    break;
                case 5:
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void plantCrop(Scanner scanner) {
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>      Available Crops:      <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
    
        int index = 1;
        for (Crop crop : cropInfo.getCrops()) {
            System.out.println(index + ". " + crop.getName());
            index++;
        }
    
        System.out.print("Enter the index of the crop to plant: ");
        int cropIndex = scanner.nextInt();
        scanner.nextLine();
    
        if (cropIndex < 1 || cropIndex > cropInfo.getCrops().size()) {
            System.out.println("Invalid crop index. Returning to menu.");
            return;
        }
    
        Crop crop = cropInfo.getCrops().get(cropIndex - 1);
    
        String seedName = crop.getName() + " seeds";
        if (!inventory.useItem(seedName, 1)) {
            System.out.println("You don't have enough " + seedName + ". Restock to plant this crop.");
            return;
        }
    
        System.out.print("Enter the planting date (MM-dd-yyyy): ");
        try {
            LocalDate plantingDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            plantedCrops.add(new PlantedCrop(crop, plantingDate));
            System.out.println("Successfully planted " + crop.getName() + " on " + plantingDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use MM-dd-yyyy.");
        }
    }    

    private void displayWateringSchedule() {
        if (plantedCrops.isEmpty()) {
            System.out.println("No crops planted yet.");
            return;
        }
    
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:><:><:><:><:><:>");
        System.out.println("<:>           Watering Schedule:           <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:><:><:><:><:><:>");
        
        for (PlantedCrop plantedCrop : plantedCrops) {
            Crop crop = plantedCrop.getCrop();
            LocalDate plantingDate = plantedCrop.getPlantingDate();
            int wateringInterval = crop.getWateringSchedule(); 

            long daysSincePlanting = LocalDate.now().toEpochDay() - plantingDate.toEpochDay();
            int daysUntilNextWatering = (int) (wateringInterval - (daysSincePlanting % wateringInterval));

            if (daysUntilNextWatering <= 0) {
                daysUntilNextWatering += wateringInterval;
            }

            System.out.println("- " + crop.getName() + " (Planted on: " + plantingDate + ") - Water in " + daysUntilNextWatering + " days");
        }
    }

    private void displayFertilizerSchedule() {
        if (plantedCrops.isEmpty()) {
            System.out.println("No crops planted yet.");
            return;
        }
    
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>    Fertilizer Schedule:    <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
    
        for (PlantedCrop plantedCrop : plantedCrops) {
            Crop crop = plantedCrop.getCrop();
            LocalDate plantingDate = plantedCrop.getPlantingDate();
            HashMap<String, Integer> fertilizerSchedule = crop.getFertilizerSchedule();
    
            System.out.println("- " + crop.getName() + " (Planted on: " + plantingDate + "):");
    
            if (fertilizerSchedule == null || fertilizerSchedule.isEmpty()) {
                System.out.println("  No fertilizer schedule available for this crop.");
                continue;
            }
    
            long daysSincePlanting = ChronoUnit.DAYS.between(plantingDate, LocalDate.now());
    
            fertilizerSchedule.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    int applicationDay = entry.getValue();
                    long daysRemaining = applicationDay - daysSincePlanting;
    
                    if (daysRemaining > 0) {
                        System.out.println("  - " + entry.getKey() + " in " + daysRemaining + " days");
                    } 
                    else if (daysRemaining == 0) {
                        System.out.println("  - " + entry.getKey() + ": Apply today!");
                    } 
                    else {
                        System.out.println("  - " + entry.getKey() + ": Missed " + Math.abs(daysRemaining) + " days ago");
                    }
                });
        }
    }        
      
    private void viewCropGrowth() {
        if (plantedCrops.isEmpty()) {
            System.out.println("No crops planted yet.");
            return;
        }
    
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>      Planted Crops and Their Growth Stages:     <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
    
        for (PlantedCrop plantedCrop : plantedCrops) {
            String cropName = plantedCrop.getCrop().getName();
            LocalDate plantingDate = plantedCrop.getPlantingDate();
            long daysSincePlanting = LocalDate.now().toEpochDay() - plantingDate.toEpochDay();
    
            String growthStage = JDBCConnector.getCurrentGrowthStage(cropName, (int) daysSincePlanting);
    
            System.out.println("- " + cropName + " (Planted on: " + plantingDate + ") - Current Growth Stage: " + growthStage);
        }
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
