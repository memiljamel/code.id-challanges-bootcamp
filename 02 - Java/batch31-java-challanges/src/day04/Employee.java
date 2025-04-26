package day04;

public class Employee {

    private int empId;
    private String fullName;
    private Role role;
    private double salary;
    private double totalSalary;

    public Employee() {
    }

    public Employee(int empId, String fullName, Role role, double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.role = role;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", salary=" + salary +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
