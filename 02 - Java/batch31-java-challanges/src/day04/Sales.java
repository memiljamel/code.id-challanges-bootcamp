package day04;

public class Sales extends Employee implements SalaryInterface {

    private Commission commission;
    private Overtime overtime;

    public Sales() {
    }

    public Sales(int empId, String fullName, Role role, double salary, Commission commission, Overtime overtime) {
        super(empId, fullName, role, salary);
        this.commission = commission;
        this.overtime = overtime;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
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
                        + commission.getCommission()
                        + commission.getBonus()
                        + overtime.getMakan()
        );
    }

    @Override
    public String toString() {
        return super.toString() + "Sales{" +
                "commission=" + commission +
                ", overtime=" + overtime +
                '}';
    }
}
