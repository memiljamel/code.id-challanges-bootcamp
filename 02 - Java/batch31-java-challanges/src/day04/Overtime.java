package day04;

import java.time.LocalDate;

public class Overtime extends Salary {

    private double makan;

    public Overtime() {
    }

    public Overtime(LocalDate payDay, double makan) {
        super(payDay);
        this.makan = makan;
    }

    public double getMakan() {
        return makan;
    }

    public void setMakan(double makan) {
        this.makan = makan;
    }

    @Override
    public String toString() {
        return super.toString() + "Overtime{" +
                "makan=" + makan +
                '}';
    }
}
