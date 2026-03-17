// TransportVehicle.java (Enhanced)
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Abstract base class for all transport vehicles with enhanced error handling
 */
public abstract class TransportVehicle {
    private static final Logger LOGGER = Logger.getLogger(TransportVehicle.class.getName());
    
    private String vehicleID;
    private int capacity;
    private String fuelType;
    private boolean isOperational;
    private double totalEarnings;

    public TransportVehicle(String vehicleID, int capacity, String fuelType) 
            throws VehicleException {
        validateVehicleInputs(vehicleID, capacity, fuelType);
        
        this.vehicleID = vehicleID;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.isOperational = true;
        this.totalEarnings = 0.0;
        
        LOGGER.info("Vehicle created: " + vehicleID);
    }

    private void validateVehicleInputs(String vehicleID, int capacity, String fuelType) 
            throws VehicleException {
        try {
            if (vehicleID == null || vehicleID.trim().isEmpty()) {
                throw new VehicleException("Vehicle ID cannot be null or empty");
            }
            if (!vehicleID.matches("[A-Z0-9]{3,10}")) {
                throw new VehicleException("Vehicle ID must be 3-10 alphanumeric characters");
            }
            if (capacity <= 0 || capacity > 200) {
                throw new VehicleException("Capacity must be between 1 and 200");
            }
            if (fuelType == null || fuelType.trim().isEmpty()) {
                throw new VehicleException("Fuel type cannot be null or empty");
            }
            if (!isValidFuelType(fuelType)) {
                throw new VehicleException("Invalid fuel type. Allowed: Petrol, Diesel, Electric, Hybrid");
            }
        } catch (VehicleException e) {
            LOGGER.log(Level.WARNING, "Vehicle validation failed", e);
            throw e;
        }
    }

    private boolean isValidFuelType(String fuelType) {
        String[] validTypes = {"Petrol", "Diesel", "Electric", "Hybrid", "CNG"};
        for (String type : validTypes) {
            if (type.equalsIgnoreCase(fuelType)) {
                return true;
            }
        }
        return false;
    }

    // Abstract methods
    public abstract double calculateFare(double distance) throws VehicleException;
    public abstract String getDetails();
    public abstract VehicleType getVehicleType();

    // Common methods with exception handling
    public void scheduleService() {
        try {
            if (!isOperational) {
                throw new VehicleException("Vehicle " + vehicleID + " is already in service");
            }
            System.out.println("🔧 Vehicle " + vehicleID + " scheduled for service.");
            this.isOperational = false;
            LOGGER.info("Vehicle " + vehicleID + " scheduled for service");
        } catch (VehicleException e) {
            System.err.println("Error scheduling service: " + e.getMessage());
        }
    }

    public void performCheckup() {
        try {
            System.out.println("🔍 Vehicle " + vehicleID + " is undergoing routine checkup.");
            // Simulate checkup process
            Thread.sleep(1000);
            this.isOperational = true;
            System.out.println("✅ Vehicle " + vehicleID + " checkup complete and is now operational.");
            LOGGER.info("Vehicle " + vehicleID + " checkup completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.WARNING, "Checkup interrupted for vehicle " + vehicleID, e);
            System.err.println("Checkup was interrupted");
        }
    }

    public void addEarnings(double amount) {
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Earnings cannot be negative");
            }
            this.totalEarnings += amount;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid earnings amount", e);
            System.err.println("Error adding earnings: " + e.getMessage());
        }
    }

    // Getters with null safety
    public String getVehicleID() { 
        return vehicleID != null ? vehicleID : "Unknown"; 
    }
    
    public int getCapacity() { return capacity; }
    public String getFuelType() { return fuelType != null ? fuelType : "Unknown"; }
    public boolean isOperational() { return isOperational; }
    public double getTotalEarnings() { return totalEarnings; }
    
    public void setOperational(boolean operational) { 
        this.isOperational = operational; 
    }

    @Override
    public String toString() {
        return String.format("%s[ID: %s, Capacity: %d, Fuel: %s, Status: %s, Earnings: $%.2f]", 
            getClass().getSimpleName(), getVehicleID(), capacity, getFuelType(), 
            isOperational ? "Operational" : "In Service", totalEarnings);
    }
}

enum VehicleType {
    BUS("Bus", 2.0),
    TAXI("Taxi", 50.0),
    TRUCK("Truck", 5.0),
    BIKE("Bike", 1.0);

    private final String displayName;
    private final double baseRate;

    VehicleType(String displayName, double baseRate) {
        this.displayName = displayName;
        this.baseRate = baseRate;
    }

    public String getDisplayName() { return displayName; }
    public double getBaseRate() { return baseRate; }
    
    public static VehicleType fromString(String text) {
        for (VehicleType type : VehicleType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}