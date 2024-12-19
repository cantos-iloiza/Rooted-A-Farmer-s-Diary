package inventory;

import java.util.Scanner;

public class InventoryManager {
    private final Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void manageInventory(Scanner scanner) {
        boolean backToMenu = false;

        while (!backToMenu) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;
                case 2:
                    updateInventory(scanner);
                    break;
                case 3:
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n\n Welcome to Inventory, Farmer!");
        System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        System.out.println("1. View Inventory");
        System.out.println("2. Update Inventory");
        System.out.println("3. Back to Main Menu");
        System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        System.out.print("Enter your choice: ");
    }

    public void updateInventory(Scanner scanner) {
        System.out.print("\n\nEnter item name: ");
        String item = scanner.nextLine();

        System.out.print("Enter quantity to add (negative to subtract): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid quantity. Please enter a valid number.");
            scanner.nextLine();
            return;
        }

        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (quantity < 0) {
            inventory.useItem(item, -quantity);
        } 
        
        else {
            inventory.restockItem(item, quantity);
        }
    }

    public void restockItem(String item, int quantity) {
        inventory.restockItem(item, quantity);
    }

    public void displayInventoryContents() {
        inventory.displayInventory();
    }
}
