package user;

import cropmanager.Crop;
import cropmanager.CropInfo;
import cropmanager.PlantedCrop;
import inventory.Inventory;
import java.util.ArrayList;
import java.util.List;
import miscellaneous.FarmerNotes;

public class FarmerUser {
    private static List<FarmerUser> registeredUsers = new ArrayList<>();
    private String username;
    private String password;
    private ArrayList<PlantedCrop> plantedCrops = new ArrayList<>();
    private List<Crop> newCrops = new ArrayList<>();
    private Inventory inventory = new Inventory();
    private FarmerNotes farmerNotes = new FarmerNotes();

    public FarmerUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static List<FarmerUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void setRegisteredUsers(List<FarmerUser> registeredUsers) {
        FarmerUser.registeredUsers = registeredUsers;
    }

    public ArrayList<PlantedCrop> getPlantedCrops() {
        return plantedCrops;
    }

    public void setPlantedCrops(ArrayList<PlantedCrop> plantedCrops) {
        this.plantedCrops = plantedCrops;
    }

    public List<Crop> getNewCrops() {
        return newCrops;
    }

    public void setNewCrops(List<Crop> newCrops) {
        this.newCrops = newCrops;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void viewAvailableCrops(CropInfo cropInfo) {
        System.out.println("\nAvailable crops:");
        int index = 1;
        for (Crop crop : cropInfo.getCrops()) {
            System.out.println(index + ". " + crop.getName());
            index++;
        }
    }
}
