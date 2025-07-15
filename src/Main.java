import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Access to university Mnagement System CLass
        UniversityManagementSystem universityManagement = new UniversityManagementSystem();
        Scanner input = new Scanner(System.in);

        int choice = 0;
        try {
            while (choice != 9) {
                // Display menu
                System.out.println("\n         -Menu-        \n");
                System.out.println("1. Check available seats ");
                System.out.println("2. Register student (with an ID) ");
                System.out.println("3. Delete student ");
                System.out.println("4. Find student (with student ID) ");
                System.out.println("5. Store student details into a file ");
                System.out.println("6. Load student details from the file to the system ");
                System.out.println("7. View the list of students based on their names ");
                System.out.println("8. Generate Summary of the system & report with list of students includes");
                System.out.println("9. Exit \n");

                System.out.print("Enter Your Choice : ");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        universityManagement.checkAvailableSeats();
                        break;
                    case 2:
                        universityManagement.registerStudent();
                        break;
                    case 3:
                        universityManagement.deleteStudent();
                        break;
                    case 4:
                        universityManagement.findStudent();
                        break;
                    case 5:
                        universityManagement.storeStudentDetails();
                        break;
                    case 6:
                        universityManagement.loadStudentDetails();
                        break;
                    case 7:
                        universityManagement.viewStudentsByName();
                        break;
                    case 8:
                        universityManagement.generateSummary_CompleteReport();
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid Choice. Please Try Again !");
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid Input. Please Enter a Valid Input!");
        }
    }
}