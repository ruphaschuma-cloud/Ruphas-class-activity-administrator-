public class Bus extends TransportVehicle {
    private String route;
    private boolean hasAC;

    public Bus(String busID, int capacity, String fuelType, String route, boolean hasAC) {
        super(busID, capacity, fuelType);
        this.route = route;
        this.hasAC = hasAC;
    }

    @Override
    public double calculateFare(double distance) {
        // Example fare calculation: base rate per km
        double ratePerKm = hasAC ? 3.0 : 2.0; // AC buses cost more
        return distance * ratePerKm;
    }

    @Override
      public void displayInfo() {
        System.out.println("Bus " + vehicleID + " | Route: " + route +
                           " | Capacity: " + capacity +
                           " | Fuel: " + fuelType +
                           " | AC: " + (hasAC ? "Yes" : "No"));
    }

    public void displayRouteInfo() {
        System.out.println("Bus " + vehicleID + " runs on route: " + route);
    }

    public void scheduleService() {
        System.out.println("Bus " + vehicleID + " scheduled for service.");
    }

    public void performCheckup() {
        System.out.println("Bus " + vehicleID + " is undergoing a routine checkup.");
    }

    @Override
    public String getDetails() {
        return "Bus ID: " + vehicleID +
               ", Route: " + route +
               ", Capacity: " + capacity +
               ", Fuel: " + fuelType +
               ", AC: " + (hasAC ? "Yes" : "No");
    }
}
