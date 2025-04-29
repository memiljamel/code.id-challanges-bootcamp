package day06.allowance;

import java.time.LocalDate;

public class Operational extends Allowance {

    private int days;
    private double launch;
    private double transport;

    public Operational() {
    }

    public Operational(LocalDate payDay, int days, double launch, double transport) {
        super(payDay);
        this.days = days;
        this.launch = launch;
        this.transport = transport;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getLaunch() {
        return launch;
    }

    public void setLaunch(double launch) {
        this.launch = launch;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return super.toString() + "Operational{" +
                "days=" + days +
                ", launch=" + launch +
                ", transport=" + transport +
                '}';
    }
}
