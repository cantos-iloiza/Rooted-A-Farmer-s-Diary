package user;

import cropmanager.Crop;
import cropmanager.CropInfo;
import inventory.InventoryManager;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AdminUser extends User {
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "password";

    public AdminUser() {
        super(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public void addNewCrop(CropInfo cropInfo, InventoryManager inventoryManager, Scanner scanner) {
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:><:>:<:><:>");
        System.out.println("<:>                     New Crop Update:                      <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:><:>:<:><:>");
        String name = getInputString(scanner, "Enter crop name: ");
        String type = getInputString(scanner, "Enter crop type: ");
        int seasonStart = getInputInt(scanner, "Enter season start (1-12): ");
        int seasonEnd = getInputInt(scanner, "Enter season end (1-12): ");
        int wateringSchedule = getInputInt(scanner, "Enter watering schedule (days): ");

        System.out.println("Adding growth stages...");
        LinkedHashMap<String, Integer> growthStages = inputGrowthStages(scanner);

        System.out.println("Adding fertilizer schedule...");
        LinkedHashMap<String, Integer> fertilizerSchedule = inputFertilizerSchedule(scanner);
        
        Crop newCrop = new Crop(name, type, seasonStart, seasonEnd, growthStages, fertilizerSchedule, wateringSchedule);
        cropInfo.addCrop(newCrop);
        inventoryManager.restockItem(name, 5);

        System.out.println("New crop added successfully!");
    }

    private String getInputString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getInputInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private LinkedHashMap<String, Integer> inputGrowthStages(Scanner scanner) {
        LinkedHashMap<String, Integer> growthStages = new LinkedHashMap<>();
        while (true) {
            String stageName = getInputString(scanner, "Enter growth stage name (or 'x' to finish): ");
            if (stageName.equalsIgnoreCase("x")) 
                break;

            int duration = getInputInt(scanner, "Enter duration for this stage (days): ");
            growthStages.put(stageName, duration);
        }
        return growthStages;
    }

    private LinkedHashMap<String, Integer> inputFertilizerSchedule(Scanner scanner) {
        LinkedHashMap<String, Integer> fertilizerSchedule = new LinkedHashMap<>();
        while (true) {
            String stageName = getInputString(scanner, "Enter fertilizer stage name (or 'x' to finish): ");
            if (stageName.equalsIgnoreCase("x")) 
                break;

            int day = getInputInt(scanner, "Enter application day for this stage: ");
            fertilizerSchedule.put(stageName, day);
        }
        return fertilizerSchedule;
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
    
            int inventoryChoice = scanner.nextInt();
            scanner.nextLine();
            switch (inventoryChoice) {
                case 1:
                    inventoryManager.displayInventoryContents();
                    break;
                case 2:
                    System.out.print("Enter item name to add or restock: ");
                    String itemName = scanner.nextLine();
    
                    System.out.print("Enter quantity to add: ");
                    int quantityToAdd = getValidatedQuantity(scanner);
    
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
    
    private int getValidatedQuantity(Scanner scanner) {
        while (true) {
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.print("Quantity must be non-negative. Please enter again: ");
                    continue;
                }
                return quantity;
            } 
            catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    } 
    
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
