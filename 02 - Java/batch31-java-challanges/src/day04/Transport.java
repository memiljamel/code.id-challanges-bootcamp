package day04;

import java.time.LocalDate;

public class Transport extends Salary {

    private double transportasi;
    private double spj;
    private double bensin;

    public Transport() {
    }

    public Transport(LocalDate payDay, double transportasi, double spj, double bensin) {
        super(payDay);
        this.transportasi = transportasi;
        this.spj = spj;
        this.bensin = bensin;
    }

    public double getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(double transportasi) {
        this.transportasi = transportasi;
    }

    public double getSpj() {
        return spj;
    }

    public void setSpj(double spj) {
        this.spj = spj;
    }

    public double getBensin() {
        return bensin;
    }

    public void setBensin(double bensin) {
        this.bensin = bensin;
    }

    @Override
    public String toString() {
        return super.toString() + "Transport{" +
                "transportasi=" + transportasi +
                ", spj=" + spj +
                ", bensin=" + bensin +
                '}';
    }
}
