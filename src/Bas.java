// Concrete Class: Bus
class Bus extends TransportVehicle implements Maintenance {
    private String routeNumber;
    private boolean hasAC;
    private String lastServiceDate;

    public Bus(String vehicleID, int capacity, String fuelType, 
               String routeNumber, boolean hasAC) throws VehicleException {
        super(vehicleID, capacity, fuelType);
        validateBusInputs(routeNumber);
        
        this.routeNumber = routeNumber;
        this.hasAC = hasAC;
        this.lastServiceDate = "Not serviced yet";
    }

    private void validateBusInputs(String routeNumber) throws VehicleException {
        if (routeNumber == null || routeNumber.trim().isEmpty()) {
            throw new VehicleException("Route number cannot be null or empty");
        }
        if (!routeNumber.matches("[A-Za-z0-9\\s-]{3,20}")) {
            throw new VehicleException("Route number must be 3-20 characters");
        }
    }

    @Override
    public double calculateFare(double distance) throws VehicleException {
        if (distance <= 0) {
            throw new VehicleException("Distance must be positive");
        }
        if (distance > 1000) {
            throw new VehicleException("Distance cannot exceed 1000 km for buses");
        }
        
        double baseRate = 2.0;
        double fare = distance * baseRate + (hasAC ? 10 : 0);
        return fare;
    }

    public void displayRouteInfo() {
        try {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("🚌 BUS INFORMATION");
            System.out.println("=".repeat(40));
            System.out.println("Vehicle ID: " + getVehicleID());
            System.out.println("Route: " + routeNumber);
            System.out.println("Capacity: " + getCapacity() + " seats");
            System.out.println("Fuel Type: " + getFuelType());
            System.out.println("AC: " + (hasAC ? "Yes ❄️" : "No"));
            System.out.println("Status: " + (isOperational() ? "Operational ✅" : "In Service 🔧"));
            System.out.println("Last Service: " + lastServiceDate);
        } catch (Exception e) {
            System.err.println("Error displaying bus info: " + e.getMessage());
        }
    }

    @Override
    public void scheduleService() {
        try {
            super.scheduleService();
            this.lastServiceDate = java.time.LocalDate.now().toString();
            System.out.println("Bus " + getVehicleID() + " scheduled for service on route " + routeNumber);
        } catch (Exception e) {
            System.err.println("Error scheduling bus service: " + e.getMessage());
        }
    }

    @Override
    public void performCheckup() {
        try {
            super.performCheckup();
            System.out.println("Bus " + getVehicleID() + " checkup completed. AC status: " + 
                             (hasAC ? "Working" : "N/A"));
        } catch (Exception e) {
            System.err.println("Error performing bus checkup: " + e.getMessage());
        }
    }

    @Override
    public String getDetails() {
        return String.format("Bus[ID: %s, Route: %s, Capacity: %d, AC: %s]", 
            getVehicleID(), routeNumber, getCapacity(), hasAC ? "Yes" : "No");
    }
}