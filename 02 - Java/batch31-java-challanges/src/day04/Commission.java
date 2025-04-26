package day04;

import java.time.LocalDate;

public class Commission extends Salary {

    private double bonus;
    private double commission;

    public Commission() {
    }

    public Commission(LocalDate payDay, double bonus, double commission) {
        super(payDay);
        this.bonus = bonus;
        this.commission = commission;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return super.toString() + "Commission{" +
                "bonus=" + bonus +
                ", commission=" + commission +
                '}';
    }
}
