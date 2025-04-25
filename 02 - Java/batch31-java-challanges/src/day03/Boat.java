package day03;

import java.time.LocalDate;

public class Boat extends VehiclePurchase {

    private LocalDate transactionDate;
    private long orderPerHours;

    public Boat() {
        totalBoat++;
    }

    public Boat(String noRegister, VehicleType vehicleType, int year, long price, long taxInYear, int seat, LocalDate transactionDate, long orderPerHours) {
        super(noRegister, vehicleType, year, price, taxInYear, seat);
        this.transactionDate = transactionDate;
        this.orderPerHours = orderPerHours;

        this.setTotal(orderPerHours);
        totalBoat++;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getOrderPerHours() {
        return orderPerHours;
    }

    public void setOrderPerHours(long orderPerHours) {
        this.orderPerHours = orderPerHours;

        this.setTotal(orderPerHours);
    }

    @Override
    public String toString() {
        return super.toString() + "Boat{" +
                "transactionDate=" + transactionDate +
                ", orderPerHours=" + orderPerHours +
                '}';
    }
}
