package day03;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Car D1001UM = new Car();
        D1001UM.setNoRegister("D 1001 UM");
        D1001UM.setVehicleType(VehicleType.SUV);
        D1001UM.setYear(2010);
        D1001UM.setPrice(350_000_000L);
        D1001UM.setTaxInYear(3_500_000L);
        D1001UM.setSeat(4);
        D1001UM.setTransactionDate(LocalDate.of(2023, 1, 10));
        D1001UM.setRent(500_000L);
        D1001UM.setDriver(150_000L);

        Car D1002UM = new Car();
        D1002UM.setNoRegister("D 1002 UM");
        D1002UM.setVehicleType(VehicleType.SUV);
        D1002UM.setYear(2010);
        D1002UM.setPrice(350_000_000L);
        D1002UM.setTaxInYear(3_500_000L);
        D1002UM.setSeat(4);
        D1002UM.setTransactionDate(LocalDate.of(2023, 1, 10));
        D1002UM.setRent(500_000L);
        D1002UM.setDriver(150_000L);

        Car D1003UM = new Car();
        D1003UM.setNoRegister("D 1003 UM");
        D1003UM.setVehicleType(VehicleType.SUV);
        D1003UM.setYear(2015);
        D1003UM.setPrice(350_000_000L);
        D1003UM.setTaxInYear(3_500_000L);
        D1003UM.setSeat(5);
        D1003UM.setTransactionDate(LocalDate.of(2023, 1, 12));
        D1003UM.setRent(500_000L);
        D1003UM.setDriver(150_000L);

        Car D1004UM = new Car();
        D1004UM.setNoRegister("D 1004 UM");
        D1004UM.setVehicleType(VehicleType.SUV);
        D1004UM.setYear(2015);
        D1004UM.setPrice(350_000_000L);
        D1004UM.setTaxInYear(3_500_000L);
        D1004UM.setSeat(5);
        D1004UM.setTransactionDate(LocalDate.of(2023, 1, 13));
        D1004UM.setRent(500_000L);
        D1004UM.setDriver(150_000L);

        Car D11UK = new Car();
        D11UK.setNoRegister("D 11 UK");
        D11UK.setVehicleType(VehicleType.Taxi);
        D11UK.setYear(2002);
        D11UK.setPrice(175_000_000L);
        D11UK.setTaxInYear(1_750_000L);
        D11UK.setSeat(4);
        D11UK.setTransactionDate(LocalDate.of(2023, 1, 12));
        D11UK.setOrder(45);
        D11UK.setOrderPerKm(4_500L);

        Car D12UK = new Car();
        D12UK.setNoRegister("D 12 UK");
        D12UK.setVehicleType(VehicleType.Taxi);
        D12UK.setYear(2015);
        D12UK.setPrice(225_000_000L);
        D12UK.setTaxInYear(2_250_000L);
        D12UK.setSeat(4);
        D12UK.setTransactionDate(LocalDate.of(2023, 1, 13));
        D12UK.setOrder(75);
        D12UK.setOrderPerKm(4_500L);

        Car D13UK = new Car();
        D13UK.setNoRegister("D 13 UK");
        D13UK.setVehicleType(VehicleType.Taxi);
        D13UK.setYear(2020);
        D13UK.setPrice(275_000_000L);
        D13UK.setTaxInYear(2_750_000L);
        D13UK.setSeat(4);
        D13UK.setTransactionDate(LocalDate.of(2023, 1, 13));
        D13UK.setOrder(90);
        D13UK.setOrderPerKm(4_500L);

        Plane ID8089 = new Plane();
        ID8089.setNoRegister("ID8089");
        ID8089.setVehicleType(VehicleType.PrivateJet);
        ID8089.setYear(2015);
        ID8089.setPrice(150_000_000_000L);
        ID8089.setTaxInYear(1_500_000_000L);
        ID8089.setSeat(12);
        ID8089.setTransactionDate(LocalDate.of(2022, 12, 23));
        ID8089.setOrderPerHours(55_000_000L);

        Plane ID8099 = new Plane();
        ID8099.setNoRegister("ID8099");
        ID8099.setVehicleType(VehicleType.PrivateJet);
        ID8099.setYear(2018);
        ID8099.setPrice(175_000_000_000L);
        ID8099.setTaxInYear(1_750_000_000L);
        ID8099.setSeat(15);
        ID8099.setTransactionDate(LocalDate.of(2022, 12, 25));
        ID8099.setOrderPerHours(75_000_000L);

        Boat Boat18 = new Boat();
        Boat18.setNoRegister("Boat18");
        Boat18.setVehicleType(VehicleType.Boat);
        Boat18.setYear(2020);
        Boat18.setPrice(2_000_000_000L);
        Boat18.setTaxInYear(20_000_000L);
        Boat18.setSeat(12);
        Boat18.setTransactionDate(LocalDate.of(2022, 12, 25));
        Boat18.setOrderPerHours(10_000_000L);

        List<VehiclePurchase> vehicles = List.of(D1001UM, D1002UM, D1003UM, D1004UM, D11UK, D12UK, D13UK, ID8089, ID8099, Boat18);

        long subTotal = 0;
        long totalTax = 0;
        long totalIncomeCar = 0;
        long totalIncomePlane = 0;
        long totalIncomeBoat = 0;

        for (VehiclePurchase vehicle : vehicles) {
            System.out.println(vehicle.toString());

            subTotal += vehicle.getTotal();
            totalTax += vehicle.getTaxInYear();

            if (vehicle instanceof Car) {
                totalIncomeCar += vehicle.getTotal();
            } else if (vehicle instanceof Plane) {
                totalIncomePlane += vehicle.getTotal();
            } else if (vehicle instanceof Boat) {
                totalIncomeBoat += vehicle.getTotal();
            }
        }

        System.out.println("\nSubTotal: " + subTotal);
        System.out.println("TotalIncomeCar: " + totalIncomeCar);
        System.out.println("TotalIncomePlane: " + totalIncomePlane);
        System.out.println("TotalIncomeBoat: " + totalIncomeBoat);
        System.out.println("TotalTax: " + totalTax);
        System.out.println("TotalCar: " + VehiclePurchase.totalCar);
        System.out.println("TotalPlane: " + VehiclePurchase.totalPlane);
        System.out.println("TotalBoat: " + VehiclePurchase.totalBoat);
    }
}
