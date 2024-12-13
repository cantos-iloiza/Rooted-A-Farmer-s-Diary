package user;

import cropmanager.PlantedCrop;
import inventory.Inventory;
import java.util.ArrayList;
import java.util.List;
import miscellaneous.FarmerNotes;

public class FarmerUser extends User {
    private static List<FarmerUser> registeredUsers = new ArrayList<>();

    public static List<FarmerUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void setRegisteredUsers(List<FarmerUser> registeredUsers) {
        FarmerUser.registeredUsers = registeredUsers;
    }
    private ArrayList<PlantedCrop> plantedCrops = new ArrayList<>();
    private Inventory inventory = new Inventory();
    private FarmerNotes farmerNotes = new FarmerNotes();

    public FarmerUser(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public static FarmerUser authenticate(String username, String password) {
        for (FarmerUser user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean register() {
        for (FarmerUser user : registeredUsers) {
            if (user.getUsername().equals(this.username)) {
                return false;
            }
        }
        registeredUsers.add(this);
        return true;
    }

    public ArrayList<PlantedCrop> getPlantedCrops() {
        return plantedCrops;
    }

    public void setPlantedCrops(ArrayList<PlantedCrop> plantedCrops) {
        this.plantedCrops = plantedCrops;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public FarmerNotes getFarmerNotes() {
        return farmerNotes;
    }

    public void setFarmerNotes(FarmerNotes farmerNotes) {
        this.farmerNotes = farmerNotes;
    }
}
