import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
        inventory.put("Peanut", 5);
        inventory.put("Monggo", 5);
        inventory.put("Sitaw", 5);
        inventory.put("Pechay", 5);
        inventory.put("Eggplant", 5);
        inventory.put("Okra", 5);
        inventory.put("Chili", 5);
        inventory.put("Squash", 5);
        inventory.put("Ampalaya", 5);
        inventory.put("Corn", 5);
        inventory.put("Fertilizer", 7);
        inventory.put("Pesticide Spray", 4);
    }

    public boolean useSeed(String cropName) {
        if (inventory.containsKey(cropName)) {
            int currentCount = inventory.get(cropName);
            if (currentCount > 0) {
                inventory.put(cropName, currentCount - 1);
                System.out.println("Used one " + cropName + " seed. Remaining: " + inventory.get(cropName));
                return true; 
            } else {
                System.out.println("No " + cropName + " seeds left in inventory.");
            }
        } else {
            System.out.println("Crop " + cropName + " not found in inventory.");
        }
        return false; 
    }

    public void displayInventory() {
        System.out.println("\n\nCurrent Inventory:");
        inventory.forEach((item, count) -> System.out.println(item + ": " + count));
    }

    public void useItem(String itemToUse, int quantity) {
        if (inventory.containsKey(itemToUse)) {
            int currentCount = inventory.get(itemToUse);
            if (currentCount >= quantity) {
                inventory.put(itemToUse, currentCount - quantity);
                System.out.println("Used " + quantity + " units of " + itemToUse + ". Remaining: " + inventory.get(itemToUse));
            } else {
                System.out.println("Not enough " + itemToUse + " in inventory.");
            }
        } else {
            System.out.println(itemToUse + " not found in inventory.");
        }
    }    

    public void restockItem(String itemToRestock, int quantityToAdd) {
        if (inventory.containsKey(itemToRestock)) {
            int currentCount = inventory.get(itemToRestock);
            inventory.put(itemToRestock, currentCount + quantityToAdd);
            System.out.println("Restocked " + quantityToAdd + " units of " + itemToRestock + ". Total now: " + inventory.get(itemToRestock));
        } else {
            System.out.println(itemToRestock + " not found in inventory.");
        }
    }
}
