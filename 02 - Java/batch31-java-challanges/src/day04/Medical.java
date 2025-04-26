package day04;

import java.time.LocalDate;

public class Medical extends Salary {

    private double kacamata;

    public Medical() {
    }

    public Medical(LocalDate payDay, double kacamata) {
        super(payDay);
        this.kacamata = kacamata;
    }

    public double getKacamata() {
        return kacamata;
    }

    public void setKacamata(double kacamata) {
        this.kacamata = kacamata;
    }

    @Override
    public String toString() {
        return super.toString() + "Medical{" +
                "kacamata=" + kacamata +
                '}';
    }
}
