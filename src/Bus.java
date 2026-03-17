public class Bus extends TransportVehicle {
    private String routeNumber;
    private boolean hasAC;

    public Bus(String vehicleID, int capacity, String fuelType, String routeNumber, boolean hasAC) {
        super(vehicleID, capacity, fuelType);
        this.routeNumber = routeNumber;
        this.hasAC = hasAC;
    }

    @Override
    public double calculateFare(double distance) {
        double rate = hasAC ? 3.0 : 2.0;
        return distance * rate;
    }

    @Override
    public String getDetails() {
        return "Bus ID: " + getVehicleID() + ", Route: " + routeNumber +
               ", Capacity: " + getCapacity() + ", Fuel: " + getFuelType();
    }

    public void displayRouteInfo() {
        System.out.println("Bus " + getVehicleID() + " operates on route " + routeNumber +
                           " | AC Available: " + hasAC);
    }

    public void scheduleService() {
        System.out.println("Bus " + getVehicleID() + " scheduled for service.");
    }

    public void performCheckup() {
        System.out.println("Bus " + getVehicleID() + " is undergoing a routine checkup.");
    }
}