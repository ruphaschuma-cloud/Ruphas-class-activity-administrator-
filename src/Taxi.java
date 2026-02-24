// Concrete Class: Taxi
class Taxi extends TransportVehicle implements Payment {
    private String driverName;
    private double baseFare;

    public Taxi(String vehicleID, int capacity, String fuelType, String driverName, double baseFare) {
        super(vehicleID, capacity, fuelType);
        this.driverName = driverName;
        this.baseFare = baseFare;
    }

    @Override
    public double calculateFare(double distance) {
        double ratePerKm = 5.0;
        return baseFare + (distance * ratePerKm);
    }

    public void assignDriver(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public double processPayment(double amount) {
        System.out.println("Processing payment of: " + amount);
        return amount;
    }

    @Override
    public void generateReceipt(double amount) {
        System.out.println("Receipt generated for: " + amount);
    }
}
