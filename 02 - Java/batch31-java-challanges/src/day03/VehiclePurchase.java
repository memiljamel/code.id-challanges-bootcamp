package day03;

public class VehiclePurchase {

    private String noRegister;
    private VehicleType vehicleType;
    private int year;
    private long price;
    private long taxInYear;
    private int seat;
    private long total;

    public static int totalCar = 0;
    public static int totalPlane = 0;
    public static int totalBoat = 0;

    public VehiclePurchase() {
    }

    public VehiclePurchase(String noRegister, VehicleType vehicleType, int year, long price, long taxInYear, int seat) {
        this.noRegister = noRegister;
        this.vehicleType = vehicleType;
        this.year = year;
        this.price = price;
        this.taxInYear = taxInYear;
        this.seat = seat;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getTaxInYear() {
        return taxInYear;
    }

    public void setTaxInYear(long taxInYear) {
        this.taxInYear = taxInYear;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VehiclePurchase{" +
                "noRegister='" + noRegister + '\'' +
                ", vehicleType=" + vehicleType +
                ", year=" + year +
                ", price=" + price +
                ", taxInYear=" + taxInYear +
                ", seat=" + seat +
                ", total=" + total +
                '}';
    }
}
