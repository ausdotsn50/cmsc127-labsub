import java.util.Scanner;
import java.util.*;

public class UniversityAppMenu {
    InstructorDAOImpl daoImpl = new InstructorDAOImpl();
    Scanner scn = new Scanner(System.in);
    
    // Implement the ff.
    // Purely menu purposes
    public void addInstructor() {
        System.out.println("Add instructor by inputting the ff. details...");
        System.out.println("Enter ID: ");
        String ID = scn.nextLine();

        System.out.println("Enter name: ");
        String name = scn.nextLine();

        System.out.println("Enter department: ");
        String deptName = scn.nextLine();

        System.out.println("Enter salary: ");
        double salary = scn.nextDouble();
        scn.nextLine();

        boolean ok = daoImpl.add(new Instructor(ID,name,deptName,salary));

        if(ok) {
            System.out.println("Instructor added succesfully.");
            scn.nextLine();
        } else {
            System.out.println("Failed adding instructor.");
        }
    }
    
    // Two choices to updateInstructor
    public void updateInstructor() {
        System.out.println("Enter ID of instructor to be updated: ");
        String ID = scn.nextLine();

        System.out.println("New name: ");
        String name = scn.nextLine();

        System.out.println("New department: ");
        String deptName = scn.nextLine();

        System.out.println("New salary: ");
        double salary = scn.nextDouble();

        boolean ok = daoImpl.update(new Instructor(ID, name, deptName, salary));
        if(ok) {
            System.out.println("Instructor updated succesfully.");
        } else {
            System.out.println("Failed updating instructor.");
        }
    }

    public void deleteInstructor() {
        System.out.println("Enter ID of instructor to be deleted.");
        String ID = scn.nextLine();

        boolean ok = daoImpl.delete(ID);
        if(ok) {
            System.out.println("Instructor deleted successfully.");
        } else {
            System.out.println("Failed deleting instructor");
        }
    }

    public void searchInstructor() {
        System.out.print("Choose which attribute to search by via its number:\n" +
            "1. ID\n" +
            "2. name\n" +
            "3. department\n" +
            "4. salary\n" +
            "Enter choice: "
        );

        String searchType = "";
        List<Instructor> results = null;
        int choice = scn.nextInt();
        scn.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Search instructor via ID: ");
                String ID = scn.nextLine();
                results = daoImpl.search("ID", ID);
                searchType = "ID";
                break;
            case 2:
                System.out.println("Search instructor via name: ");
                String name = scn.nextLine();
                results = daoImpl.search("name", name);
                searchType = "name";
                break;
            case 3:
                System.out.println("Search instructor via department: ");
                String deptName = scn.nextLine();
                results = daoImpl.search("dept_name", deptName);
                searchType = "department";
                break;
            case 4:
                System.out.println("Search instructor via salary: ");
                String salary = scn.nextLine();
                results = daoImpl.search("salary", salary);
                searchType = "salary";
                break;
            default:
                break;
        }

        if(results != null && !results.isEmpty()) {
            System.out.println("Search for instructor via " + searchType + " is successful.");
            for(Instructor instructor : results) {
                System.out.println("ID: " + instructor.getID() + ", Name: " + instructor.getName() + 
                    ", Department: " + instructor.getDeptName() + ", Salary: " + instructor.getSalary());
            }
        } else {
            System.out.println("Search for instructor failed or no results found.");
        }

    }

    // Return value or result set of instructors?
    public void listAllInstructors() {

    }

}
