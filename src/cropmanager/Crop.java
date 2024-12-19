package cropmanager;

import java.time.LocalDate;
import java.util.HashMap;

public class Crop {
    private String name;
    private String type;
    private int seasonStart;
    private int seasonEnd;    
    private int wateringSchedule;
    private HashMap<String, Integer> growthStages; 
    private HashMap<String, Integer> fertilizerSchedule; 
    private LocalDate plantingDate;

    public Crop(String name, String type, int seasonStart, int seasonEnd, int wateringSchedule, HashMap<String, Integer> growthStages, HashMap<String, Integer> fertilizerSchedule, LocalDate plantingDate) {
        this.name = name;
        this.type = type;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.wateringSchedule = wateringSchedule;
        this.growthStages = growthStages;
        this.fertilizerSchedule = fertilizerSchedule;
        this.plantingDate = plantingDate;
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

    public int getSeasonEnd() { 
        return seasonEnd; 
    }

    public void setSeasonEnd(int seasonEnd) { 
        this.seasonEnd = seasonEnd; 
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public HashMap<String, Integer> getGrowthStages() {
        return growthStages;
    }

    public void setGrowthStages(HashMap<String, Integer> growthStages) { 
        this.growthStages = growthStages; 
    }

    public HashMap<String, Integer> getFertilizerSchedule() {
        return fertilizerSchedule;
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

    public void addGrowthStage(String stageName, int days) {
        growthStages.put(stageName, days);
    }

    public void addFertilizerApplication(String applicationName, int days) {
        fertilizerSchedule.put(applicationName, days);
    }

    public int getDaysSincePlanting(int plantingYear, int plantingMonth, int plantingDay) {
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        java.time.LocalDate plantDate = java.time.LocalDate.of(plantingYear, plantingMonth, plantingDay);
        java.time.Period period = java.time.Period.between(plantDate, currentDate);

        return period.getDays();
    }

    @Override
    public String toString() {
        return "\nCrop: " + name + 
               "\nType: " + type + 
               "\nSeason: " + seasonStart + " to " + seasonEnd +
               "\nWatering every " + wateringSchedule + " days" +
               "\nFertilizer schedule: " + fertilizerSchedule;
    }

    public void setFertilizerSchedule(HashMap<String, Integer> fertilizerSchedule) {
        this.fertilizerSchedule = fertilizerSchedule;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }
}
