public class Student extends module {
    // these two lines have access only inside the student class
    private String studentName;
    private String studentId;
    private String module;

    // public constructor for the Student class
    public Student(String studentName, String studentId, double module_01, double module_02, double module_03, String module) {
        super(module_01, module_02, module_03);
        this.studentName = studentName;
        this.studentId = studentId;
        this.module = module;
    }


    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }


}
