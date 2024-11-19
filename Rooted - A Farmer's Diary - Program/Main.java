import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<PlantedCrop> plantedCrops = new ArrayList<>();
    private static LocalDate userDate;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nWelcome to Rooted: A Farmer's Diary!");
            userDate = initializeDate(scanner);

            System.out.println("Press Enter to Start!");
            scanner.nextLine();

            boolean appRunning = true;

            while (appRunning) {
                FarmerUser user = null;

                while (user == null) {
                    System.out.println("\n\n\n1. Login");
                    System.out.println("2. Sign Up");
                    System.out.println("3. Exit");
                    System.out.print("Choose an option: ");
                    String option = scanner.nextLine();

                    switch (option) {
                        case "1" -> user = handleLogin(scanner);
                        case "2" -> user = handleSignup(scanner);
                        case "3" -> {
                            System.out.println("Exiting Rooted. Goodbye!");
                            appRunning = false;
                            return;
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }

                CropInfo cropInfo = new CropInfo();
                cropInfo.initializeCrops();
                Inventory inventory = new Inventory();

                boolean userLoggedIn = true;
                while (userLoggedIn) {
                    System.out.println("\n\n\n\n\nWelcome to Rooted, Farmer " + user.username + "!");
                    System.out.println("Today is " + userDate);
                    System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
                    System.out.println("Main Menu:");
                    System.out.println("1. Crop Management");
                    System.out.println("2. PlantWiki");
                    System.out.println("3. Farmer's Notes");
                    System.out.println("4. Inventory");
                    System.out.println("5. Log Out");
                    System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
                    System.out.print("Enter your choice: ");
                    
                    int choice = getValidatedChoice(scanner);
                    switch (choice) {
                        case 1 -> new CropManagement(cropInfo, user.getInventory())
                                   .manageCrops(scanner, user.getPlantedCrops());
                        case 2 -> cropInfo.viewPlantWiki(scanner);
                        case 3 -> user.getFarmerNotes().manageNotes(scanner);
                        case 4 -> new InventoryManager(user.getInventory())
                                   .manageInventory(scanner);
                        case 5 -> {
                            System.out.println("Time to hit the haystack and recharge--your farm awaits your return!");
                            userLoggedIn = false;
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }

    private static LocalDate initializeDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter today's date (MM-dd-yyyy): ");
            String dateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            try {
                return LocalDate.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    private static int getValidatedChoice(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static FarmerUser handleLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        FarmerUser user = FarmerUser.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome back, Farmer " + username + "!");
        } else {
            System.out.println("Login failed. Please try again or choose to Sign Up.");
        }
        return user;
    }
    
    private static FarmerUser handleSignup(Scanner scanner) {
        System.out.print("Enter username for new account: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        FarmerUser newUser = new FarmerUser(username, password);
        if (newUser.register()) {
            System.out.println("Registration successful! Welcome, Farmer " + username + "!");
            return newUser;
        } else {
            System.out.println("Registration failed. Username already exists. Please try again.");
            return null;
        }
    }    
}
