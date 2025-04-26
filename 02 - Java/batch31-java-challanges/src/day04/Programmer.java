package day04;

public class Programmer extends Employee implements SalaryInterface {

    private Transport transport;
    private Overtime overtime;

    public Programmer() {
    }

    public Programmer(int empId, String fullName, Role role, double salary, Transport transport, Overtime overtime) {
        super(empId, fullName, role, salary);
        this.transport = transport;
        this.overtime = overtime;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
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
                        + transport.getTransportasi()
                        + transport.getSpj()
                        + transport.getBensin()
                        + overtime.getMakan()
        );
    }

    @Override
    public String toString() {
        return super.toString() + "Programmer{" +
                "transport=" + transport +
                ", overtime=" + overtime +
                '}';
    }
}
