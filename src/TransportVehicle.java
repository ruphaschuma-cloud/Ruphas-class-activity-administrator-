
// Abstract Class: TransportVehicle
abstract class TransportVehicle {
    private String vehicleID;
    private int capacity;
    private String fuelType;

    public TransportVehicle(String vehicleID, int capacity, String fuelType) {
        this.vehicleID = vehicleID;
        this.capacity = capacity;
        this.fuelType = fuelType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public abstract double calculateFare(double distance);

    public String getDetails() {
        return "VehicleID: " + vehicleID + ", Capacity: " + capacity + ", Fuel: " + fuelType;
    }
}
