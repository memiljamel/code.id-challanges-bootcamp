package day06.allowance;

import java.time.LocalDate;

public class Allowance {

    private LocalDate payDay;
    private double totalAllowance;

    public Allowance() {
    }

    public Allowance(LocalDate payDay) {
        this.payDay = payDay;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

    @Override
    public String toString() {
        return "Allowance{" +
                "payDay=" + payDay +
                ", totalAllowance=" + totalAllowance +
                '}';
    }
}
