package inventory;

import db.JDBCConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private HashMap<String, Integer> inventory;

    public Inventory() {
        this.inventory = new HashMap<>();
        loadInventoryFromDatabase();
    }

    private void loadInventoryFromDatabase() {
        try (Connection conn = JDBCConnector.getConnection()) {
            String query = "SELECT * FROM inventory";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                inventory.put(rs.getString("item_name"), rs.getInt("quantity"));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading inventory from database.");
        }
    }

    public void displayInventory() {
        System.out.println("\n<:>      Current Inventory:     <:>");
        int index = 1;
        for (String item : inventory.keySet()) {
            System.out.println(index + ". " + item + ": " + inventory.get(item));
            index++;
        }
    }

    public boolean useItem(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            if (currentQuantity >= quantity) {
                inventory.put(itemName, currentQuantity - quantity);
                System.out.println("Used " + quantity + " of " + itemName + ". Remaining: " + inventory.get(itemName));
                return true;
            } 
            else {
                System.out.println("Not enough " + itemName + ". Available: " + currentQuantity);
                return false;
            }
        } 
        else {
            System.out.println(itemName + " does not exist in inventory.");
            return false;
        }
    }

    public void restockItem(String itemName, int quantity) {
        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity);
        System.out.println("Restocked " + quantity + " of " + itemName);
    }

    public void saveInventory(String username) {
    try (Connection conn = JDBCConnector.getConnection()) {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String query = "UPDATE inventory SET quantity = ? WHERE item_name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, entry.getValue());
                stmt.setString(2, entry.getKey());
                stmt.executeUpdate();
            }
        }
    } 
    catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error saving inventory for " + username);
    }
}

}
