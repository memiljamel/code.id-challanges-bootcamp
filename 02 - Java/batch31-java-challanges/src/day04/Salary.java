package day04;

import java.time.LocalDate;

public abstract class Salary {

    private LocalDate payDay;

    public Salary() {
    }

    public Salary(LocalDate payDay) {
        this.payDay = payDay;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "payDay=" + payDay +
                '}';
    }
}
