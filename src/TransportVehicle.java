public abstract class TransportVehicle {
    protected String vehicleID;
    protected int capacity;
    protected String fuelType;

    public TransportVehicle(String vehicleID, int capacity, String fuelType) {
        this.vehicleID = vehicleID;
        this.capacity = capacity;
        this.fuelType = fuelType;
    }

    // Abstract method: subclasses must implement their own fare calculation
    public abstract double calculateFare(double distance);

    // Abstract method: subclasses must implement their own display logic
    public abstract void displayInfo();

    // Concrete method: common details for all vehicles
    public String getDetails() {
        return "VehicleID: " + vehicleID +
               ", Capacity: " + capacity +
               ", FuelType: " + fuelType;
    }

    // Getters
    public String getVehicleID() { return vehicleID; }
    public int getCapacity() { return capacity; }
    public String getFuelType() { return fuelType; }
}
