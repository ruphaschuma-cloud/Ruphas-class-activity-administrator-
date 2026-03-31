public class Taxi extends TransportVehicle {
    private String driverName;
    private double baseFare;

    public Taxi(String taxiID, int capacity, String fuelType, String driverName, double baseFare) {
        super(taxiID, capacity, fuelType);
        this.driverName = driverName;
        this.baseFare = baseFare;
    }

    @Override
    public void displayInfo() {
        System.out.println("Taxi " + vehicleID + " | Driver: " + driverName +
                           " | Capacity: " + capacity +
                           " | Fuel: " + fuelType +
                           " | Base Fare: " + baseFare);
    }

    public void assignDriver(String driverName) {
        this.driverName = driverName;
        System.out.println("Driver " + driverName + " assigned to Taxi " + vehicleID);
    }

    // Payment-like methods, but inside Taxi itself
    public double processPayment(double amount) {
        System.out.println("Payment of " + amount + " processed for Taxi " + vehicleID);
        return amount;
    }

    public void generateReceipt(double amount) {
        System.out.println("Receipt: Taxi " + vehicleID +
                           " | Driver: " + driverName +
                           " | Amount Paid: " + amount);
    }

    public String getDetails() {
        return "Taxi ID: " + vehicleID +
               ", Driver: " + driverName +
               ", Capacity: " + capacity +
               ", Fuel: " + fuelType +
               ", Base Fare: " + baseFare;
    }
    @Override
public double calculateFare(double distance) {
    return baseFare + (distance * 5.0); // example: base fare + per km rate
}
}
