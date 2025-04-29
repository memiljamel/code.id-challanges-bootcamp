package day06.employee;

import day06.constant.Status;
import day06.service.POService;
import day06.service.TaxProject;

import java.time.LocalDate;

public class Freelancer extends Employee implements POService, TaxProject {

    private double PO;
    private double ppn;

    public Freelancer() {
    }

    public Freelancer(int empId, String fullName, LocalDate hireDate, Status status, double salary, double PO, double ppn) {
        super(empId, fullName, hireDate, status, salary);
        this.PO = PO;
        this.ppn = ppn;
    }

    public double getPO() {
        return PO;
    }

    public void setPO(double PO) {
        this.PO = PO;
    }

    public double getPpn() {
        return ppn;
    }

    public void setPpn(double ppn) {
        this.ppn = ppn;
    }

    @Override
    public double totalPO() {
        this.setTotalSalary(PO - this.getPpn());

        return this.getTotalSalary();
    }

    @Override
    public void calcPpn() {
        this.setPpn(PO * ppn);
    }

    @Override
    public String toString() {
        return super.toString() + "Freelancer{" +
                "PO=" + PO +
                ", ppn=" + ppn +
                '}';
    }
}
