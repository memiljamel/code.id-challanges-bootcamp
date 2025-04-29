package day06.employee;

import day06.allowance.Allowance;
import day06.allowance.Insurance;
import day06.allowance.Operational;
import day06.allowance.Overtime;
import day06.constant.Status;
import day06.service.AllowanceService;

import java.time.LocalDate;
import java.util.List;

public class Permanent extends Employee implements AllowanceService {

    private List<Allowance> allowances;
    private double pph;
    private double tapera;

    public Permanent() {
    }

    public Permanent(int empId, String fullName, LocalDate hireDate, Status status, double salary, List<Allowance> allowances, double pph, double tapera) {
        super(empId, fullName, hireDate, status, salary);
        this.allowances = allowances;
        this.pph = pph;
        this.tapera = tapera;
    }

    public List<Allowance> getAllowances() {
        return allowances;
    }

    public void setAllowances(List<Allowance> allowances) {
        this.allowances = allowances;
    }

    public double getPph() {
        return pph;
    }

    public void setPph(double pph) {
        this.pph = pph;
    }

    public double getTapera() {
        return tapera;
    }

    public void setTapera(double tapera) {
        this.tapera = tapera;
    }

    @Override
    public void calcTotalSalary() {
        double totalAllowance = 0;
        double totalInsurance = 0;
        double totalTax = this.getPph() + this.getTapera();

        for (Allowance allowance : allowances) {
            if (allowance instanceof Overtime overtime) {
                overtime.setTotalAllowance(overtime.getHours() * overtime.getOvertime());
                totalAllowance += overtime.getTotalAllowance();
            } else if (allowance instanceof Operational operational) {
                operational.setTotalAllowance((operational.getLaunch() + operational.getTransport()) * operational.getDays());
                totalAllowance += operational.getTotalAllowance();
            } else if (allowance instanceof Insurance insurance) {
                insurance.setTotalAllowance((super.getSalary() * insurance.getMedical()) * (insurance.getSelf() + insurance.getDependent()));
                totalInsurance += insurance.getTotalAllowance();
            }
        }

        super.setTotalSalary((super.getSalary() + totalAllowance) - (totalInsurance + totalTax));
    }

    @Override
    public void calcPph() {
        this.setPph(super.getSalary() * this.getPph());
    }

    @Override
    public void calcTapera() {
        this.setTapera(super.getSalary() * this.getTapera());
    }

    @Override
    public String toString() {
        return super.toString() + "Permanent{" +
                "allowances=" + allowances +
                ", pph=" + pph +
                ", tapera=" + tapera +
                '}';
    }
}
