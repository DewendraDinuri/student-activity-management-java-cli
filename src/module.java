public class module {
    private  double module_01;
    private  double module_02;
    private  double module_03;
    public   String moduleGrade;

    //public constructor for the module class
    public module(double module_01, double module_02, double module_03) {
        this.module_01 = module_01;
        this.module_02 = module_02;
        this.module_03 = module_03;
        calculateModuleGrade();
    }


    public double getModule_01() {
        return module_01;
    }

    public double getModule_02() {
        return module_02;
    }

    public double getModule_03() {
        return module_03;
    }

    public String getmoduleGrade() {
        return moduleGrade;
    }
    private void calculateModuleGrade() {
        double averageMarks = (module_01 + module_02 + module_03) / 3;
        if (averageMarks >= 80) {
            this.moduleGrade = "Distinction";
        } else if (averageMarks >= 70) {
            this.moduleGrade = "Merit";
        } else if (averageMarks >= 40) {
            this.moduleGrade = "Pass";
        } else {
            this.moduleGrade = "Fail";
        }
    }
}



