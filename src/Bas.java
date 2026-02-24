// Concrete Class: Bus
class Bus extends TransportVehicle implements Maintenance {
    private String routeNumber;
    private boolean hasAC;

    public Bus(String vehicleID, int capacity, String fuelType, String routeNumber, boolean hasAC) {
        super(vehicleID, capacity, fuelType);
        this.routeNumber = routeNumber;
        this.hasAC = hasAC;
    }

    @Override
    public double calculateFare(double distance) {
        double baseRate = 2.0;
        return distance * baseRate + (hasAC ? 10 : 0);
    }

    public void displayRouteInfo() {
        System.out.println("Bus Route: " + routeNumber + ", AC: " + hasAC);
    }

    @Override
    public void scheduleService() {
        System.out.println("Bus scheduled for service.");
    }

    @Override
    public void performCheckup() {
        System.out.println("Bus checkup completed.");
    }
}
