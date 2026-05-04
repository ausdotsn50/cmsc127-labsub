public class Instructor {
    private String ID;
    private String name;
    private String deptName;
    private double salary;

    public Instructor(String ID, String name, String deptName, double salary) {
        this.ID=ID;
        this.name=name;
        this.deptName=deptName;
        this.salary=salary;
    }

    // Instructor getters
    public String getID() {return ID;}
    public String getName() {return name;}
    public String getDeptName() {return deptName;}
    public double getSalary() {return salary;}

    // Instructor setters
    public void setID(String ID) {this.ID=ID;}
    public void setName(String name) {this.name=name;}
    public void setDeptName(String deptName) {this.deptName=deptName;}
    public void setSalary(double salary) {this.salary=salary;}
}
