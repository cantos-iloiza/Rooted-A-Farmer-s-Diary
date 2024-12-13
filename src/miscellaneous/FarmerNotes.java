package miscellaneous;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FarmerNotes extends NotesManager {
    private final ArrayList<Note> notes = new ArrayList<>();

    public void manageNotes(Scanner scanner) {
        boolean backToMenu = false;

        while (!backToMenu) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n\nWelcome to Farmer's Notes, Farmer!");
        System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        System.out.println("1. Add Note");
        System.out.println("2. View Notes");
        System.out.println("3. Back to Main Menu");
        System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void addNote(Scanner scanner) {
        LocalDate noteDate = getNoteDate(scanner);

        System.out.print("Enter your note: ");
        String noteContent = scanner.nextLine();
        notes.add(new Note(noteDate, noteContent));
        System.out.println("Note added successfully!");
    }

    private LocalDate getNoteDate(Scanner scanner) {
        LocalDate noteDate = null;

        while (noteDate == null) {
            System.out.print("\n\nEnter date for note (MM-dd-yyyy): ");
            String dateInput = scanner.nextLine();

            try {
                noteDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            } 
            
            catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        return noteDate;
    }

    @Override
    public void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } 
        
        else {
            System.out.println("\n<:>:<:>:<:>:<:>:<:>:<:>:<:><:>:<:>");
            System.out.println("<:>      Viewing all notes:    <:>");
            System.out.println("<:>:<:>:<:>:<:>:<:>:<:>:<:><:>:<:>");
            
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Note: " + content;
    }
}
