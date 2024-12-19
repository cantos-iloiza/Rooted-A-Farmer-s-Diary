package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class JDBCConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbms_rooted";
    private static final String USER = "root";
    private static final String PASSWORD = "cantosiloy";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, Integer> getFertilizerSchedule(String cropName) {
        HashMap<String, Integer> fertilizerSchedule = new HashMap<>();
        String query = """
                SELECT cfs.first_application_day, cfs.second_application_day, cfs.third_application_day
                FROM crop_fertilizer_schedule cfs
                JOIN crops c ON cfs.crop_id = c.id
                WHERE c.name = ?;
                """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cropName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fertilizerSchedule.put("First Application", rs.getInt("first_application_day"));
                fertilizerSchedule.put("Second Application", rs.getInt("second_application_day"));
                fertilizerSchedule.put("Third Application", rs.getInt("third_application_day"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching fertilizer schedule: " + e.getMessage());
            e.printStackTrace();
        }
        return fertilizerSchedule;
    }

    public static String getCurrentGrowthStage(String cropName, int daysSincePlanting) {
        String growthStage = "Unknown Stage";
        String query = """
                SELECT cgs.sprouting_day, cgs.seeding_day, cgs.budding_day, cgs.flowering_day, cgs.harvest_day
                FROM crop_growth_stages cgs
                JOIN crops c ON cgs.crop_id = c.id
                WHERE c.name = ?;
                """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cropName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int sproutingDay = rs.getInt("sprouting_day");
                int seedingDay = rs.getInt("seeding_day");
                int buddingDay = rs.getInt("budding_day");
                int floweringDay = rs.getInt("flowering_day");
                int harvestDay = rs.getInt("harvest_day");

                if (daysSincePlanting < sproutingDay) {
                    growthStage = "Not yet sprouted";
                } else if (daysSincePlanting < seedingDay) {
                    growthStage = "Sprouting";
                } else if (daysSincePlanting < buddingDay) {
                    growthStage = "Seeding";
                } else if (daysSincePlanting < floweringDay) {
                    growthStage = "Budding";
                } else if (daysSincePlanting < harvestDay) {
                    growthStage = "Flowering";
                } else {
                    growthStage = "Ready for Harvest";
                }
            }
        } 
        catch (SQLException e) {
            System.err.println("Error fetching growth stage: " + e.getMessage());
            e.printStackTrace();
        }
        return growthStage;
    }
}
