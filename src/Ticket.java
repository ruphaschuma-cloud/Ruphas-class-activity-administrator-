public class Taxi extends TransportVehicle implements Payment {
    private String driverName;
    private double baseFare;

    public Taxi(String vehicleID, int capacity, String fuelType, String driverName, double baseFare) {
        super(vehicleID, capacity, fuelType);
        this.driverName = driverName;
        this.baseFare = baseFare;
    }

    @Override
    public double calculateFare(double distance) {
        return baseFare + (distance * 10);
    }

    @Override
    public String getDetails() {
        return "Taxi ID: " + getVehicleID() + ", Driver: " + driverName +
               ", Capacity: " + getCapacity() + ", Fuel: " + getFuelType();
    }

    public void assignDriver(String driverName) {
        this.driverName = driverName;
        System.out.println("Driver assigned: " + driverName);
    }

    @Override
    public double processPayment(double amount) {
        System.out.println("Payment of " + amount + " processed for Taxi " + getVehicleID());
        return amount;
    }

    @Override
    public void generateReceipt(double amount) {
        System.out.println("Receipt: Taxi " + getVehicleID() + " | Amount Paid: " + amount);
    }

    public void scheduleService() {
        System.out.println("Taxi " + getVehicleID() + " scheduled for service.");
    }

    public void performCheckup() {
        System.out.println("Taxi " + getVehicleID() + " is undergoing a routine checkup.");
    }
}