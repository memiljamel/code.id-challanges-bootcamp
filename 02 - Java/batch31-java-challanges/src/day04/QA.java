package day04;

public class QA extends Employee implements SalaryInterface {

    private Overtime overtime;

    public QA() {
    }

    public QA(int empId, String fullName, Role role, double salary, Overtime overtime) {
        super(empId, fullName, role, salary);
        this.overtime = overtime;
    }

    public Overtime getOvertime() {
        return overtime;
    }

    public void setOvertime(Overtime overtime) {
        this.overtime = overtime;
    }

    @Override
    public void calculateSalary() {
        super.setTotalSalary(
                super.getSalary()
                        + overtime.getMakan()
        );
    }

    @Override
    public String toString() {
        return super.toString() + "QA{" +
                "overtime=" + overtime +
                '}';
    }
}
