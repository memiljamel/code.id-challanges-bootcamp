package day03;

import java.time.LocalDate;

public class Car extends VehiclePurchase {

    private LocalDate transactionDate;
    private long rent;
    private long driver;
    private int order;
    private long orderPerKm;

    public Car() {
        totalCar++;
    }

    public Car(String noRegister, VehicleType vehicleType, int year, long price, long taxInYear, int seat, LocalDate transactionDate, long rent, long driver, int order, long orderPerKm) {
        super(noRegister, vehicleType, year, price, taxInYear, seat);
        this.transactionDate = transactionDate;
        this.rent = rent;
        this.driver = driver;
        this.order = order;
        this.orderPerKm = orderPerKm;

        this.setTotal((rent + driver) + (order * orderPerKm));
        totalCar++;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getRent() {
        return rent;
    }

    public void setRent(long rent) {
        this.rent = rent;

        this.setTotal((rent + driver) + (order * orderPerKm));
    }

    public long getDriver() {
        return driver;
    }

    public void setDriver(long driver) {
        this.driver = driver;

        this.setTotal((rent + driver) + (order * orderPerKm));
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;

        this.setTotal((rent + driver) + (order * orderPerKm));
    }

    public long getOrderPerKm() {
        return orderPerKm;
    }

    public void setOrderPerKm(long orderPerKm) {
        this.orderPerKm = orderPerKm;

        this.setTotal((rent + driver) + (order * orderPerKm));
    }

    @Override
    public String toString() {
        return super.toString() + "Car{" +
                "transactionDate=" + transactionDate +
                ", rent=" + rent +
                ", driver=" + driver +
                ", order=" + order +
                ", orderPerKm=" + orderPerKm +
                '}';
    }
}
