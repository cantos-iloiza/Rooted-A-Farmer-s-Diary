import java.util.Scanner;

public class InventoryManager {
    private final Inventory inventory; 

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public void manageInventory(Scanner scanner) {
        boolean backToMenu = false;

        while (!backToMenu) {
            System.out.println("\n\n Welcome to Inventory, Farmer!");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
            System.out.println("1. View Inventory");
            System.out.println("2. Update Inventory");
            System.out.println("3. Back to Main Menu");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> inventory.displayInventory();
                case 2 -> updateInventory(scanner);
                case 3 -> backToMenu = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void updateInventory(Scanner scanner) {
        System.out.print("\n\nEnter item name: ");
        String item = scanner.nextLine();
    
        System.out.print("Enter quantity to add (negative to subtract): ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
    
        if (quantity < 0) {
            inventory.useItem(item, -quantity); 
        } 
        
        else {
            inventory.restockItem(item, quantity);  
        }
    }
}
