package cropmanager;

import java.util.HashMap;

public class Crop {
    private String name;
    private String type;
    private int seasonStart;
    private int seasonEnd;
    private HashMap<String, Integer> growthStages;
    private HashMap<String, Integer> fertilizerSchedule;
    private int wateringSchedule;

    public Crop(String name, String type, int seasonStart, int seasonEnd, HashMap<String, Integer> growthStages, HashMap<String, Integer> fertilizerSchedule, int wateringSchedule) {
        this.name = name;
        this.type = type;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.growthStages = growthStages;
        this.fertilizerSchedule = fertilizerSchedule;
        this.wateringSchedule = wateringSchedule;
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getType() { 
        return type; 
    }
    public void setType(String type) { 
        this.type = type; 
    }

    public int getSeasonStart() { 
        return seasonStart; 
    }
    public void setSeasonStart(int seasonStart) { 
        this.seasonStart = seasonStart; 
    }

    public int getSeasonEnd() { return seasonEnd; }
    public void setSeasonEnd(int seasonEnd) { 
        this.seasonEnd = seasonEnd; 
    }

    public HashMap<String, Integer> getGrowthStages() { 
        return growthStages; 
    }

    public void setGrowthStages(HashMap<String, Integer> growthStages) { 
        this.growthStages = growthStages; 
    }

    public HashMap<String, Integer> getFertilizerSchedule() { return fertilizerSchedule; }
    public void setFertilizerSchedule(HashMap<String, Integer> fertilizerSchedule) { 
        this.fertilizerSchedule = fertilizerSchedule; 
    }

    public int getWateringSchedule() { 
        return wateringSchedule; 
    }
    public void setWateringSchedule(int wateringSchedule) { 
        this.wateringSchedule = wateringSchedule; 
    }

    public String getCurrentGrowthStage(int daysSincePlanting) {
        for (String stage : growthStages.keySet()) {
            if (daysSincePlanting <= growthStages.get(stage)) {
                return stage;
            }
        }
        return "Mature";
    }

    @Override
    public String toString() {
        return "\nCrop: " + name + 
               "\nType: " + type + 
               "\nSeason: " + seasonStart + " to " + seasonEnd +
               "\nWatering every " + wateringSchedule + " days" +
               "\nFertilizer schedule: " + fertilizerSchedule;
    }
}
