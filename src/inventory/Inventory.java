package inventory;

import cropmanager.CropInfo;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> inventory;
    private CropInfo cropInfo;

    public Inventory() {
        this.inventory = new HashMap<>();
        initializeDefaultInventory();
    }

    private void initializeDefaultInventory() {
        String[] crops = { "Peanut", "Monggo", "Sitaw", "Pechay", "Eggplant", "Okra", "Chili", "Squash", "Ampalaya", "Corn" };
        for (String crop : crops) {
            inventory.put(crop, 5);
        }
        inventory.put("Fertilizer", 7);
        inventory.put("Pesticide Spray", 4);
    }

    public void displayInventory() {
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
        System.out.println("<:>      Current Inventory:     <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");

        int index = 1;
        for (String cropName : inventory.keySet()) {
            System.out.println(index + ". " + cropName + ": " + inventory.get(cropName));
            index++;
        }
    }

    public void useItem(String itemToUse, int quantity) {
        if (inventory.containsKey(itemToUse) && inventory.get(itemToUse) >= quantity) {
            inventory.put(itemToUse, inventory.get(itemToUse) - quantity);
            System.out.println("Used " + quantity + " units of " + itemToUse + ". Remaining: " + inventory.get(itemToUse));
        } 
        
        else {
            System.out.println((inventory.containsKey(itemToUse) ?
                    "Not enough " + itemToUse + " in inventory." : itemToUse + " not found."));
        }
    }

    public void restockItem(String itemToRestock, int quantityToAdd) {
        inventory.put(itemToRestock, inventory.getOrDefault(itemToRestock, 0) + quantityToAdd);
        System.out.println("Restocked " + quantityToAdd + " units of " + itemToRestock + ". Total now: " + inventory.get(itemToRestock));
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void addOrUpdateItem(String itemName, int quantity) {
        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity);
        System.out.println("Item '" + itemName + "' updated with quantity " + quantity);
    }

    public boolean useSeed(String seedName) {
        if (inventory.containsKey(seedName) && inventory.get(seedName) > 0) {
            inventory.put(seedName, inventory.get(seedName) - 1);
            System.out.println("Used 1 seed of " + seedName + ". Remaining: " + inventory.get(seedName));
            return true;
        } 
        
        else {
            System.out.println("No seeds of " + seedName + " available.");
            return false;
        }
    }

    public boolean useSeed(int cropIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CropInfo getCropInfo() {
        return cropInfo;
    }

    public void setCropInfo(CropInfo cropInfo) {
        this.cropInfo = cropInfo;
    }
}
