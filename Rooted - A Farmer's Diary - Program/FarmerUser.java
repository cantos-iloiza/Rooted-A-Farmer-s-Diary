import java.util.ArrayList;
import java.util.List;

public class FarmerUser {
    public String username;
    private String password;
    private ArrayList<PlantedCrop> plantedCrops = new ArrayList<>();
    private Inventory inventory = new Inventory();
    private FarmerNotes farmerNotes = new FarmerNotes();
    private static List<FarmerUser> registeredUsers = new ArrayList<>();

    public FarmerUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ArrayList<PlantedCrop> getPlantedCrops() {
        return plantedCrops;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public FarmerNotes getFarmerNotes() {
        return farmerNotes;
    }

    public static FarmerUser login(String username, String password) {
        for (FarmerUser user : registeredUsers) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user; 
            }
        }
        return null;
    }

    public boolean register() {
        for (FarmerUser user : registeredUsers) {
            if (user.username.equals(this.username)) {
                return false;
            }
        }
        registeredUsers.add(this);
        return true; 
    }
}
