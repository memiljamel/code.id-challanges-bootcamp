package day06.allowance;

import java.time.LocalDate;

public class Overtime extends Allowance {

    private int hours;
    private double overtime;

    public Overtime() {
    }

    public Overtime(LocalDate payDay, int hours, double overtime) {
        super(payDay);
        this.hours = hours;
        this.overtime = overtime;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return super.toString() + "Overtime{" +
                "hours=" + hours +
                ", overtime=" + overtime +
                '}';
    }
}
