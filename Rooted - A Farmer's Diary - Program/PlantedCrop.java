import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlantedCrop {
    private Crop crop;
    private LocalDate plantingDate;

    public PlantedCrop(Crop crop, LocalDate plantingDate) {
        this.crop = crop;
        this.plantingDate = plantingDate;
    }

    public Crop getCrop() {
        return crop;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public String getGrowthStage() {
        long days = ChronoUnit.DAYS.between(plantingDate, LocalDate.now());
        return crop.getCurrentGrowthStage((int) days);
    }
}
