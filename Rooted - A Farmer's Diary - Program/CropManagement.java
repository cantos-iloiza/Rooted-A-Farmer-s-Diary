import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
            System.out.println("1. Plant a crop");
            System.out.println("2. View watering schedule");
            System.out.println("3. View fertilizer schedule");
            System.out.println("4. View crop growth");
            System.out.println("5. Return to main menu");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
            System.out.print("Enter your choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1 -> plantCrop(scanner);
                case 2 -> displayWateringSchedule();
                case 3 -> displayFertilizerSchedule();
                case 4 -> viewCropGrowth();
                case 5 -> {
                    System.out.println("Returning to the main menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }    

    public void plantCrop(Scanner scanner) {
        System.out.println("\n\nAvailable crops:");
        cropInfo.getCrops().forEach(crop -> System.out.println("- " + crop.getName()));
        System.out.print("Enter the crop name to plant: ");
        String cropName = scanner.nextLine();

        Crop crop = getCrop(cropName);
        if (crop == null) {
            System.out.println("Invalid crop name. Returning to menu.");
            return;
        }

        if (!inventory.useSeed(cropName)) {
            System.out.println("Unable to plant crop. Please restock seeds.");
            return; 
        }

        System.out.print("Enter the planting date (yyyy-MM-dd): ");
        try {
            LocalDate plantingDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            plantedCrops.add(new PlantedCrop(crop, plantingDate));
            System.out.println("Successfully planted " + crop.getName() + " on " + plantingDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
    }

    private void displayWateringSchedule() {
        if (plantedCrops.isEmpty()) {
            System.out.println("No crops planted yet.");
            return;
        }
    
        System.out.println("\n\nWatering Schedule:");
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
    
        System.out.println("\n\nFertilizer Schedule:");
        for (PlantedCrop plantedCrop : plantedCrops) {
            Crop crop = plantedCrop.getCrop();
            LocalDate plantingDate = plantedCrop.getPlantingDate();
            HashMap<String, Integer> fertilizerSchedule = crop.getFertilizerSchedule();
    
            System.out.println("- " + crop.getName() + " (Planted on: " + plantingDate + "):");
            long daysSincePlanting = LocalDate.now().toEpochDay() - plantingDate.toEpochDay();
    
            String[] stages = {"First Application", "Second Application", "Third Application"};
            boolean hasPendingApplications = false;
    
            for (String stage : stages) {
                if (fertilizerSchedule.containsKey(stage)) {
                    int scheduledDay = fertilizerSchedule.get(stage);
                    int daysRemaining = scheduledDay - (int) daysSincePlanting;
    
                    if (daysRemaining > 0) {
                        hasPendingApplications = true;
                        System.out.println("  - " + stage + " in " + daysRemaining + " days");
                    }
                }
            }
    
            if (!hasPendingApplications) {
                System.out.println("  - All fertilizer applications completed.");
            }
        }
    }
    
    private void viewCropGrowth() {
        if (plantedCrops.isEmpty()) {
            System.out.println("No crops planted yet.");
            return;
        }
    
        System.out.println("\n\nPlanted Crops and Their Growth Stages:");
        for (PlantedCrop plantedCrop : plantedCrops) {
            Crop crop = plantedCrop.getCrop();
            LocalDate plantingDate = plantedCrop.getPlantingDate();
            long daysSincePlanting = LocalDate.now().toEpochDay() - plantingDate.toEpochDay();
            String growthStage = crop.getCurrentGrowthStage((int) daysSincePlanting);
    
            System.out.println("- " + crop.getName() + " (Planted on: " + plantingDate + ") - Current Growth Stage: " + growthStage);
        }
    }

    public Crop getCrop(String cropName) {
        for (Crop crop : cropInfo.getCrops()) {
            if (crop.getName().equalsIgnoreCase(cropName)) {
                return crop;
            }
        }
        return null;
    }
}
