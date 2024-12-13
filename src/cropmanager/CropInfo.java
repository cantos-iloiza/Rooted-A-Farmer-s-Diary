package cropmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public final class CropInfo {
    private static CropInfo instance;
    private ArrayList<Crop> crops = new ArrayList<>();
    private Set<String> newCrops = new HashSet<>();

    private CropInfo() {
        this.crops = initializeCrops();
    }

    public static CropInfo getInstance() {
        if (instance == null) {
            instance = new CropInfo();
        }
        return instance;
    }

    public ArrayList<String> getCropNames() {
        ArrayList<String> cropNames = new ArrayList<>();
        for (Crop crop : crops) {
            cropNames.add(crop.getName());
        }
        return cropNames;
    }

    public ArrayList<Crop> initializeCrops() {
        ArrayList<Crop> localCrops = new ArrayList<>();

        // Peanut
        HashMap<String, Integer> peanutGrowth = new HashMap<>();
        peanutGrowth.put("Sprouting", 15);
        peanutGrowth.put("Seeding", 45);
        peanutGrowth.put("Budding", 85);
        peanutGrowth.put("Flowering", 110);
        peanutGrowth.put("Harvest", 132);

        LinkedHashMap<String, Integer> peanutFertilizer = new LinkedHashMap<>();
        peanutFertilizer.put("First Application", 20);
        peanutFertilizer.put("Second Application", 45);
        peanutFertilizer.put("Third Application", 100);
        localCrops.add(new Crop("Peanut", "Legume", 2, 3, peanutGrowth, peanutFertilizer, 7));

        // Monggo (Mung Beans)
        HashMap<String, Integer> monggoGrowth = new HashMap<>();
        monggoGrowth.put("Sprouting", 10);
        monggoGrowth.put("Seeding", 30);
        monggoGrowth.put("Budding", 60);
        monggoGrowth.put("Flowering", 80);
        monggoGrowth.put("Harvest", 100);

        LinkedHashMap<String, Integer> monggoFertilizer = new LinkedHashMap<>();
        monggoFertilizer.put("First Application", 12);
        monggoFertilizer.put("Second Application", 23);
        monggoFertilizer.put("Third Application", 55);
        localCrops.add(new Crop("Monggo", "Legume", 6, 9, monggoGrowth, monggoFertilizer, 9));

        // Sitaw (String Beans)
        HashMap<String, Integer> sitawGrowth = new HashMap<>();
        sitawGrowth.put("Sprouting", 8);
        sitawGrowth.put("Seeding", 20);
        sitawGrowth.put("Budding", 35);
        sitawGrowth.put("Flowering", 50);
        sitawGrowth.put("Harvest", 65);

        LinkedHashMap<String, Integer> sitawFertilizer = new LinkedHashMap<>();
        sitawFertilizer.put("First Application", 10);
        sitawFertilizer.put("Second Application", 18);
        sitawFertilizer.put("Third Application", 33);
        localCrops.add(new Crop("Sitaw", "Legume", 1, 12, sitawGrowth, sitawFertilizer, 1));

        // Pechay (Chinese Cabbage)
        HashMap<String, Integer> pechayGrowth = new HashMap<>();
        pechayGrowth.put("Sprouting", 5);
        pechayGrowth.put("Seeding", 12);
        pechayGrowth.put("Budding", 20);
        pechayGrowth.put("Flowering", 28);
        pechayGrowth.put("Harvest", 35);

        LinkedHashMap<String, Integer> pechayFertilizer = new LinkedHashMap<>();
        pechayFertilizer.put("First Application", 7);
        pechayFertilizer.put("Second Application", 12);
        pechayFertilizer.put("Third Application", 18);
        localCrops.add(new Crop("Pechay", "Leafy Vegetable", 9, 2, pechayGrowth, pechayFertilizer, 3));

        // Eggplant
        HashMap<String, Integer> eggplantGrowth = new HashMap<>();
        eggplantGrowth.put("Sprouting", 10);
        eggplantGrowth.put("Seeding", 20);
        eggplantGrowth.put("Budding", 40);
        eggplantGrowth.put("Flowering", 55);
        eggplantGrowth.put("Harvest", 70);

        LinkedHashMap<String, Integer> eggplantFertilizer = new LinkedHashMap<>();
        eggplantFertilizer.put("First Application", 12);
        eggplantFertilizer.put("Second Application", 20);
        eggplantFertilizer.put("Third Application", 38);
        localCrops.add(new Crop("Eggplant", "Fruiting Vegetable", 12, 8, eggplantGrowth, eggplantFertilizer, 1));

        // Okra
        HashMap<String, Integer> okraGrowth = new HashMap<>();
        okraGrowth.put("Sprouting", 8);
        okraGrowth.put("Seeding", 18);
        okraGrowth.put("Budding", 35);
        okraGrowth.put("Flowering", 48);
        okraGrowth.put("Harvest", 60);

        LinkedHashMap<String, Integer> okraFertilizer = new LinkedHashMap<>();
        okraFertilizer.put("First Application", 10);
        okraFertilizer.put("Second Application", 18);
        okraFertilizer.put("Third Application", 33);
        localCrops.add(new Crop("Okra", "Fruiting Vegetable", 1, 8, okraGrowth, okraFertilizer, 8));

        // Chili
        HashMap<String, Integer> chiliGrowth = new HashMap<>();
        chiliGrowth.put("Sprouting", 10);
        chiliGrowth.put("Seeding", 20);
        chiliGrowth.put("Budding", 40);
        chiliGrowth.put("Flowering", 55);
        chiliGrowth.put("Harvest", 75);

        LinkedHashMap<String, Integer> chiliFertilizer = new LinkedHashMap<>();
        chiliFertilizer.put("First Application", 12);
        chiliFertilizer.put("Second Application", 20);
        chiliFertilizer.put("Third Application", 38);
        localCrops.add(new Crop("Chili", "Fruiting Vegetable", 10, 3, chiliGrowth, chiliFertilizer, 1));

        // Squash
        HashMap<String, Integer> squashGrowth = new HashMap<>();
        squashGrowth.put("Sprouting", 12);
        squashGrowth.put("Seeding", 24);
        squashGrowth.put("Budding", 45);
        squashGrowth.put("Flowering", 60);
        squashGrowth.put("Harvest", 80);

        LinkedHashMap<String, Integer> squashFertilizer = new LinkedHashMap<>();
        squashFertilizer.put("First Application", 14);
        squashFertilizer.put("Second Application", 24);
        squashFertilizer.put("Third Application", 43);
        localCrops.add(new Crop("Squash", "Squash", 1, 12, squashGrowth, squashFertilizer, 1));

        // Ampalaya (Bitter Gourd)
        HashMap<String, Integer> ampalayaGrowth = new HashMap<>();
        ampalayaGrowth.put("Sprouting", 8);
        ampalayaGrowth.put("Seeding", 20);
        ampalayaGrowth.put("Budding", 35);
        ampalayaGrowth.put("Flowering", 50);
        ampalayaGrowth.put("Harvest", 65);

        LinkedHashMap<String, Integer> ampalayaFertilizer = new LinkedHashMap<>();
        ampalayaFertilizer.put("First Application", 10);
        ampalayaFertilizer.put("Second Application", 20);
        ampalayaFertilizer.put("Third Application", 33);
        localCrops.add(new Crop("Ampalaya", "Gourd", 1, 12, ampalayaGrowth, ampalayaFertilizer, 2));

        // Corn
        HashMap<String, Integer> cornGrowth = new HashMap<>();
        cornGrowth.put("Sprouting", 10);
        cornGrowth.put("Seeding", 25);
        cornGrowth.put("Budding", 50);
        cornGrowth.put("Flowering", 75);
        cornGrowth.put("Harvest", 100);

        LinkedHashMap<String, Integer> cornFertilizer = new LinkedHashMap<>();
        cornFertilizer.put("First Application", 12);
        cornFertilizer.put("Second Application", 25);
        cornFertilizer.put("Third Application", 48);
        localCrops.add(new Crop("Corn", "Grain", 10, 3, cornGrowth, cornFertilizer, 4));

        return localCrops;
    }

    public void addCrop(Crop crop) {
        crops.add(crop);
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public void viewPlantWiki(Scanner scanner) {
        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:><:>");
        System.out.println("<:>    Welcome to PlantWiki!   <:>");
        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:><:>");

        for (int i = 0; i < crops.size(); i++) {
            System.out.println((i + 1) + ". " + crops.get(i).getName());
        }    

        String input;
        do {
            System.out.print("Enter the crop number to view details, or 'x' to return: ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("x")) {
                try {
                    int index = Integer.parseInt(input) - 1; 
                    if (index >= 0 && index < crops.size()) {
                        Crop selectedCrop = crops.get(index);

                        System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");
                        System.out.println("               Details for " + selectedCrop.getName() + "               ");
                        System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>:<:>");

                        System.out.println("Type: " + selectedCrop.getType());
                        System.out.println("Season: " + selectedCrop.getSeasonStart() + " to " + selectedCrop.getSeasonEnd());
                        System.out.println("Watering schedule: Every " + selectedCrop.getWateringSchedule() + " days");
    
                        System.out.println("Fertilizer schedule: ");
                        selectedCrop.getFertilizerSchedule().forEach((key, value) -> 
                            System.out.println(key + " on day " + value)
                        );
                    } 
                    
                    else {
                        System.out.println("Invalid number. Try again.");
                    }
                } 
                
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a valid number.");
                }
            }
        } 
        
        while (!input.equalsIgnoreCase("x"));
    }

    public void setCrops(ArrayList<Crop> crops) {
        this.crops = crops;
    }

    public Set<String> getNewCrops() {
        return newCrops;
    }

    public void setNewCrops(Set<String> newCrops) {
        this.newCrops = newCrops;
    }
}
