package miscellaneous;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FarmerNotes {
    private ArrayList<Note> notes = new ArrayList<>();

    public void manageNotes(Scanner scanner) {
        String choice;
        do {
            System.out.println("\n\nWelcome to Farmer's Notes, Farmer!");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Back to Main Menu");
            System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<");
            System.out.print("Enter your choice: ");

            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addNote(scanner);
                case "2" -> viewNotes();
                case "3" -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (!choice.equals("3"));
    }

    private void addNote(Scanner scanner) {
        LocalDate noteDate = null;
        while (noteDate == null) {
            System.out.print("\n\nEnter date for note (MM-dd-yyyy): ");
            String dateInput = scanner.nextLine();
            try {
                noteDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in MM-dd-yyyy format.");
            }
        }

        System.out.print("Enter your note: ");
        String noteContent = scanner.nextLine();
        notes.add(new Note(noteDate, noteContent));
        System.out.println("Note added successfully!");
    }

    private void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } else {
            System.out.println("\n\nViewing all notes:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }
}


class Note {
    private LocalDate date;
    private String content;

    public Note(LocalDate date, String content) {
        this.date = date;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Note: " + content;
    }
}
