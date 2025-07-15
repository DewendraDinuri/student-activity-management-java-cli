import java.io.*;
import java.util.Scanner;

public class UniversityManagementSystem {
    //constant  global intake capacity variable
    private static final int Max_Student = 100;
    // create Array to store student details
    private static Student[] students = new Student[Max_Student];
    public static int StudentCount = 0;

    static Scanner input = new Scanner(System.in);

    //1. Check available seats
    public static void checkAvailableSeats() {
        System.out.println((Max_Student - StudentCount) + " Seats Available ");
    }

    //2. Register student (with an ID)
    public static void registerStudent() {
        try {

            if (StudentCount >= Max_Student) {
                System.out.println("No Available Seats!");
            } else {
                String studentId;
                String studentName ;

                while (true) {
                    System.out.print("Enter Student Name: ");
                    studentName = input.nextLine();

                    // Check validity of the Name
                    if (isValidString(studentName)) {
                        break;
                    } else {
                        System.err.println("ERROR: Invalid String format. Please try again.");
                    }
                }

                while (true) {
                    System.out.print("Enter Student ID [e.g. w1234567]: ");
                    studentId = input.nextLine();

                    // Check validity of the ID
                    if (isValidId(studentId)) {
                        break;
                    } else {
                        System.err.println("ERROR: Invalid ID format. Please try again.");
                    }
                }

                System.out.print("Enter Your Module_1 Marks: ");
                double module_01 = input.nextDouble();

                System.out.print("Enter Your Module_2 Marks: ");
                double module_02 = input.nextDouble();

                System.out.print("Enter Your Module_3 Marks: ");
                double module_03 = input.nextDouble();

                // Consume the leftover newline
                input.nextLine();
                //Access to module CLass
                module module = new module(module_01,module_02,module_03);
                students[StudentCount] = new Student(studentName, studentId, module_01, module_02, module_03,module.getmoduleGrade());
                StudentCount++; // Increment the studentCount when each student is loaded

                printStudentDetails();
                System.out.println("Registered Successfully!");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ERROR: Array Index Out Of Bounds");
        } catch (NullPointerException e) {
            System.err.println("Null Pointer Exception");
        }
    }
    // Method to check if the given string is valid
    // A valid string is non-null, non-empty after trimming, and contains only alphabetic characters
    private static boolean isValidString(String str) {
        //string is not null, not empty after trimming, and matches the regex for alphabetic characters
        return str != null && !str.trim().isEmpty() && str.matches("[a-zA-Z]+");
    }
    // Method to check if the given ID is valid
    // A valid ID matches the pattern "w" followed by exactly 7 digits
    private static boolean isValidId(String id) {
        //ID matches the regex for the pattern "w" followed by 7 digits
        return id.matches("w\\d{7}");
    }

    //printing method this for register method and delete method to view before delete
    public static void printStudentDetails() {
        System.out.println("Current registered students:");
        for (int i = 0; i < StudentCount; i++) {
            if (students[i] != null) {
                System.out.println(students[i].getStudentName() + "," + students[i].getStudentId() + "," + students[i].getModule_01() + "," + students[i].getModule_02() + "," + students[i].getModule_03() + "," + " Module Grade: " + students[i].getmoduleGrade());
            }
        }
    }

    // 3. Delete student from array
    public static void deleteStudent() {
        printStudentDetails();
        try {
            System.out.print("Enter Id that you want to delete student details: ");
            String expectedStudentId = input.nextLine();
            boolean studentFound = false;
            for (int i = 0; i < StudentCount; i++) {
                if (students[i] != null && students[i].getStudentId().equals(expectedStudentId)) {
                    students[i] = students[StudentCount - 1];
                    students[StudentCount - 1] = null;
                    StudentCount--;
                    studentFound = true;
                    System.out.println("Student deleted successfully!");
                    break;
                }
            }
            if (!studentFound) {
                System.out.println("Student not found!");
            }

        } catch (NullPointerException e) {
            System.err.println("ERROR: Some Indexes are Null");
        }
    }

    // 4. Find student (with student ID)
    public void findStudent() {
        System.out.print("Enter student ID to find: ");
        String findId = input.nextLine();
        boolean studentFound = false;
        for (int i = 0; i < StudentCount; i++) {
            if (students[i] != null && students[i].getStudentId().equals(findId)) {
                System.out.println(students[i].getStudentName() + "," + students[i].getStudentId() + "," + students[i].getModule_01() + "," + students[i].getModule_02() + "," + students[i].getModule_03() + "," + " Module Grade: " + students[i].getmoduleGrade());
                studentFound = true;
                break;
            }
        }
        if (!studentFound) {
            System.out.println("Student not found.");
        }
    }

    // 5. Store student details into a file
    public static void storeStudentDetails() {
        File studentDetailsFile = new File("Student_Details.txt");
        try {
            boolean fileCreated = studentDetailsFile.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + studentDetailsFile.getName());
            } else {
                if (studentDetailsFile.exists()) {
                    System.out.println("File already exists.");
                } else {
                    System.out.println("Error while creating file: " + studentDetailsFile.getName());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(studentDetailsFile))) {
            for (int i = 0; i < StudentCount; i++) {
                if (students[i] != null) {
                    writer.write(students[i].getStudentName() + "," + students[i].getStudentId() + "," + students[i].getModule_01() + "," + students[i].getModule_02() + "," + students[i].getModule_03() + "," + " Module Grade: " + students[i].getmoduleGrade() + "\n");
                }
            }
            writer.close();
            System.out.println("Student details stored successfully!");
        } catch (IOException e) {
            System.err.println("ERROR: Unable to store student details");
        }
    }

    // 6. Load student details from the file to the system
    public static void loadStudentDetails() {
        File studentDetailsFile = new File("Student_Details.txt");
        if (!studentDetailsFile.exists()) {
            System.out.println("File not found: " + studentDetailsFile.getName());
            return;
        }

        try (Scanner studentDetailsFileReader = new Scanner(studentDetailsFile)) {
            while (studentDetailsFileReader.hasNextLine()) {
                String line = studentDetailsFileReader.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 6 && StudentCount < Max_Student) {
                    String studentName = parts[0];
                    String studentId = parts[1];
                    double module_01 = Double.parseDouble(parts[2]);
                    double module_02 = Double.parseDouble(parts[3]);
                    double module_03 = Double.parseDouble(parts[4]);
                    String moduleGrade = parts[5].replace(" Module Grade: ", "");

                    module module = new module(module_01,module_02,module_03);
                    students[StudentCount] = new Student(studentName, studentId, module_01, module_02, module_03, module.moduleGrade);
                    StudentCount++; // Increment the studentCount when each student is loaded
                }
            }
            System.out.println("Student details loaded successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // 7. View the list of students based on their names using sort algorithm
    public static void viewStudentsByName() {
        for (int i = 0; i < StudentCount - 1; i++) {
            for (int j = 0; j < StudentCount - 1 - i; j++) {
                if (students[j].getStudentName().compareTo(students[j + 1].getStudentName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        System.out.println("List of students sorted by name:");
        for (int i = 0; i < StudentCount; i++) {
            if (students[i] != null) {
                System.out.println(students[i].getStudentName());
            }
        }
    }

    //C. Generate Summary & CompleteReport
    public static void generateSummary_CompleteReport() {
        while (true) {
            System.out.print("Enter 'c' for Generate Summary or 'd' for Generate Report: ");
            String userInput = input.nextLine().toLowerCase();

            if (userInput.equals("c")) {
                int totalRegistrations = StudentCount;
                int studentScore = 0;
                for (int i = 0; i < StudentCount; i++) {
                    if (students[i].getModule_01() > 40 && students[i].getModule_02() > 40 && students[i].getModule_03() > 40) {
                        studentScore++;
                    }
                }
                System.out.printf("------------------------------------------------------------------------------------------%n");
                System.out.println("                                   Summary of the System                 ");
                System.out.printf("------------------------------------------------------------------------------------------%n");
                System.out.println("      Total student registrations: " + totalRegistrations);
                System.out.println("      Total number of students who scored more than 40 marks in Module 1,2,and 3: " + studentScore);
                System.out.printf("------------------------------------------------------------------------------------------%n");
                break;
            } else if (userInput.equals("d")) {
                // Sort students by average marks in descending order using bubble sort
                for (int i = 0; i < StudentCount - 1; i++) {
                    for (int j = 0; j < StudentCount - 1 - i; j++) {
                        if (calculateAverage(students[j]) < calculateAverage(students[j + 1])) {
                            Student temp = students[j];
                            students[j] = students[j + 1];
                            students[j + 1] = temp;
                        }
                    }
                }
                System.out.printf("-----------------------------------------------%n");
                System.out.printf("   Complete Report with List of Students    %n");
                System.out.printf("-----------------------------------------------%n");
                for (int i = 0; i < StudentCount; i++) {
                    double totalMarks = students[i].getModule_01() + students[i].getModule_02() + students[i].getModule_03();
                    double averageMarks = calculateAverage(students[i]);
                    String grade = students[i].getmoduleGrade();


                    System.out.println("        Student ID: " + students[i].getStudentId());
                    System.out.println("        Student Name: " + students[i].getStudentName());
                    System.out.println("        Module 1 Marks: " + students[i].getModule_01());
                    System.out.println("        Module 2 Marks: " + students[i].getModule_02());
                    System.out.println("        Module 3 Marks: " + students[i].getModule_03());
                    System.out.println("        Total Marks: " + totalMarks );
                    System.out.println("        Average Marks: " + averageMarks );
                    System.out.println("        Grade: " + grade );
                    System.out.printf("-----------------------------------------------%n");
                }
                break;
            } else {
                System.out.println("Invalid input. Please enter 'c' or 'd'.");
            }
        }
    }

    private static double calculateAverage(Student student) {
        return (student.getModule_01() + student.getModule_02() + student.getModule_03()) / 3;
    }
}