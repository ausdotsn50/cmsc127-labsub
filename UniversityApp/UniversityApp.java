import java.util.Scanner;

// Created the menu interface for CLI
public class UniversityApp {
    static Scanner scn = new Scanner(System.in);
    
    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        UniversityAppMenu menu = new UniversityAppMenu();
        int choice;
        boolean running = true;
        while (running) {
            System.out.println("Instructor University App Database Menu");
            System.out.print(
                "1. Add instructor\n" + 
                "2. Update instructor details\n" +
                "3. Delete instructor\n" +
                "4. Search instructor\n" +
                "5. List all instructor\n" +
                "6. Exit\n" +
                "Enter choice: "
            );
            choice = scn.nextInt();

            switch (choice) {
                case 1:
                    menu.addInstructor();
                    break;
                case 2:
                    menu.updateInstructor();
                    break;
                case 3:
                    menu.deleteInstructor();
                    break;
                case 4:
                    menu.searchInstructor();
                    break;
                case 5:
                    menu.listAllInstructors();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    running = false;
                default:
                    break;
            }
        }
    }
}
