public abstract class TransportVehicle {
    private String vehicleID;
    private int capacity;
    private String fuelType;

    public TransportVehicle(String vehicleID, int capacity, String fuelType) {
        this.vehicleID = vehicleID;
        this.capacity = capacity;
        this.fuelType = fuelType;
    }

    // Abstract method
    public abstract double calculateFare(double distance);

    // Concrete method
    public String getDetails() {
        return "VehicleID: " + vehicleID + ", Capacity: " + capacity + ", FuelType: " + fuelType;
    }

    // Getters
    public String getVehicleID() { return vehicleID; }
    public int getCapacity() { return capacity; }
    public String getFuelType() { return fuelType; }
}