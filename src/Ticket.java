// Taxi.java (Enhanced)
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Enhanced Taxi class with comprehensive exception handling
 */
class Taxi extends TransportVehicle implements Payment {
    private static final Logger LOGGER = Logger.getLogger(Taxi.class.getName());
    
    private String driverName;
    private String driverLicense;
    private double ratePerKm;
    private String licensePlate;
    private List<String> tripHistory;
    private List<Transaction> transactions;
    private String lastTransactionId;

    public Taxi(String vehicleID, int capacity, String fuelType, 
                String driverName, double ratePerKm) throws VehicleException {
        super(vehicleID, capacity, fuelType);
        validateTaxiInputs(driverName, ratePerKm);
        
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.driverLicense = "UNKNOWN";
        this.licensePlate = "TEMP-" + vehicleID;
        this.tripHistory = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public Taxi(String vehicleID, int capacity, String fuelType, 
                String driverName, String driverLicense, 
                String licensePlate, double ratePerKm) throws VehicleException {
        this(vehicleID, capacity, fuelType, driverName, ratePerKm);
        validateAdditionalTaxiInputs(driverLicense, licensePlate);
        
        this.driverLicense = driverLicense;
        this.licensePlate = licensePlate;
    }

    private void validateTaxiInputs(String driverName, double ratePerKm) 
            throws VehicleException {
        if (driverName == null || driverName.trim().isEmpty()) {
            throw new VehicleException("Driver name cannot be null or empty");
        }
        if (!driverName.matches("[A-Za-z\\s-]{2,50}")) {
            throw new VehicleException("Driver name must contain only letters, spaces, or hyphens");
        }
        if (ratePerKm <= 0 || ratePerKm > 1000) {
            throw new VehicleException("Rate per km must be between 0.01 and 1000");
        }
    }

    private void validateAdditionalTaxiInputs(String driverLicense, String licensePlate) 
            throws VehicleException {
        if (driverLicense == null || driverLicense.trim().isEmpty()) {
            throw new VehicleException("Driver license cannot be null or empty");
        }
        if (!driverLicense.matches("[A-Z0-9]{6,15}")) {
            throw new VehicleException("Driver license must be 6-15 alphanumeric characters");
        }
        if (licensePlate == null || licensePlate.trim().isEmpty()) {
            throw new VehicleException("License plate cannot be null or empty");
        }
    }

    @Override
    public double calculateFare(double distance) throws VehicleException {
        try {
            if (distance <= 0) {
                throw new VehicleException("Distance must be positive");
            }
            if (distance > 500) {
                throw new VehicleException("Taxi trips cannot exceed 500 km");
            }
            return distance * ratePerKm;
        } catch (VehicleException e) {
            LOGGER.log(Level.WARNING, "Fare calculation error for taxi " + getVehicleID(), e);
            throw e;
        }
    }

    public void assignDriver(String newDriver) {
        try {
            if (newDriver == null || newDriver.trim().isEmpty()) {
                throw new VehicleException("New driver name cannot be null or empty");
            }
            
            String oldDriver = this.driverName;
            this.driverName = newDriver;
            System.out.println("👤 Driver for taxi " + getVehicleID() + " changed from " + 
                              oldDriver + " to " + newDriver);
            LOGGER.info("Driver changed for taxi " + getVehicleID());
            
        } catch (VehicleException e) {
            LOGGER.log(Level.WARNING, "Invalid driver assignment", e);
            System.err.println("Error assigning driver: " + e.getMessage());
        }
    }

    public void assignDriver(String newDriver, String newLicense) {
        try {
            if (newDriver == null || newDriver.trim().isEmpty()) {
                throw new VehicleException("New driver name cannot be null or empty");
            }
            if (newLicense == null || newLicense.trim().isEmpty()) {
                throw new VehicleException("New license cannot be null or empty");
            }
            
            this.driverName = newDriver;
            this.driverLicense = newLicense;
            System.out.println("👤 Driver for taxi " + getVehicleID() + " updated: " + 
                              newDriver + " (License: " + newLicense + ")");
            LOGGER.info("Driver updated for taxi " + getVehicleID());
            
        } catch (VehicleException e) {
            LOGGER.log(Level.WARNING, "Invalid driver assignment", e);
            System.err.println("Error assigning driver: " + e.getMessage());
        }
    }

    public void addTrip(String startLocation, String endLocation, double distance) {
        try {
            if (startLocation == null || startLocation.trim().isEmpty()) {
                throw new VehicleException("Start location cannot be null or empty");
            }
            if (endLocation == null || endLocation.trim().isEmpty()) {
                throw new VehicleException("End location cannot be null or empty");
            }
            if (distance <= 0) {
                throw new VehicleException("Distance must be positive");
            }
            
            double fare = calculateFare(distance);
            String trip = String.format("Trip: %s → %s (%.1f km) - $%.2f", 
                startLocation, endLocation, distance, fare);
            tripHistory.add(trip);
            addEarnings(fare);
            
            LOGGER.info("Trip added for taxi " + getVehicleID() + ": " + trip);
            
        } catch (VehicleException e) {
            LOGGER.log(Level.WARNING, "Error adding trip", e);
            System.err.println("Error adding trip: " + e.getMessage());
        }
    }

    @Override
    public double processPayment(double amount, PaymentMethod method) {
        try {
            if (!isOperational()) {
                throw new PaymentException("Taxi " + getVehicleID() + " is not operational!");
            }
            if (amount <= 0) {
                throw new PaymentException("Payment amount must be positive");
            }
            if (method == null) {
                throw new PaymentException("Payment method cannot be null");
            }

            System.out.println("\n💳 Processing payment for taxi " + getVehicleID());
            System.out.println("Amount: $" + amount);
            System.out.println("Method: " + method.getDisplayName());
            
            // Process payment based on method
            double driverShare = amount * 0.8; // Driver gets 80%
            double companyShare = amount * 0.2; // Company gets 20%
            
            System.out.println("Driver (" + driverName + ") receives: $" + driverShare);
            System.out.println("Company receives: $" + companyShare);
            
            // Create transaction record
            Transaction transaction = new Transaction(getVehicleID(), amount, method);
            transactions.add(transaction);
            lastTransactionId = transaction.getTransactionId();
            
            addEarnings(amount);
            LOGGER.info("Payment processed for taxi " + getVehicleID() + ": $" + amount);
            
            return driverShare;
            
        } catch (PaymentException e) {
            LOGGER.log(Level.WARNING, "Payment processing error", e);
            System.err.println("Payment error: " + e.getMessage());
            return 0.0;
        }
    }

    @Override
    public double processPayment(double amount) {
        return processPayment(amount, PaymentMethod.CASH);
    }

    @Override
    public void generateReceipt(double amount) {
        try {
            if (amount <= 0) {
                throw new PaymentException("Invalid receipt amount");
            }
            
            Ticket ticket = TicketFactory.createTaxiTicket("Passenger", this, amount / ratePerKm);
            ticket.printTicket();
            LOGGER.info("Receipt generated for taxi " + getVehicleID());
            
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error generating receipt", e);
            System.err.println("Error generating receipt: " + e.getMessage());
        }
    }

    @Override
    public boolean refundPayment(String transactionId, double amount) {
        try {
            if (transactionId == null || transactionId.trim().isEmpty()) {
                throw new PaymentException("Transaction ID cannot be null or empty");
            }
            if (amount <= 0) {
                throw new PaymentException("Refund amount must be positive");
            }
            
            System.out.println("🔄 Processing refund for transaction " + transactionId);
            System.out.println("Refund amount: $" + amount);
            
            // Find and update transaction
            for (Transaction t : transactions) {
                if (t.getTransactionId().equals(transactionId)) {
                    t.setSuccessful(false);
                    System.out.println("✅ Refund processed successfully");
                    LOGGER.info("Refund processed for transaction " + transactionId);
                    return true;
                }
            }
            
            throw new PaymentException("Transaction not found: " + transactionId);
            
        } catch (PaymentException e) {
            LOGGER.log(Level.WARNING, "Refund processing error", e);
            System.err.println("Refund error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getLastTransactionId() {
        return lastTransactionId;
    }

    public void displayTripHistory() {
        try {
            System.out.println("\n📋 Trip History for Taxi " + getVehicleID());
            if (tripHistory.isEmpty()) {
                System.out.println("No trips recorded yet.");
            } else {
                for (int i = 0; i < tripHistory.size(); i++) {
                    System.out.println((i + 1) + ". " + tripHistory.get(i));
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error displaying trip history", e);
            System.err.println("Error displaying trip history: " + e.getMessage());
        }
    }

    @Override
    public String getDetails() {
        return String.format("Taxi %s [Driver: %s, License: %s, Plate: %s, Rate: $%.2f/km]", 
            getVehicleID(), driverName, driverLicense, licensePlate, ratePerKm);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TAXI;
    }

    // Getters with null safety
    public String getDriverName() { 
        return driverName != null ? driverName : "Unknown"; 
    }
    public String getDriverLicense() { 
        return driverLicense != null ? driverLicense : "Unknown"; 
    }
    public String getLicensePlate() { 
        return licensePlate != null ? licensePlate : "Unknown"; 
    }
    public double getRatePerKm() { return ratePerKm; }
    public List<String> getTripHistory() { 
        return tripHistory != null ? new ArrayList<>(tripHistory) : new ArrayList<>(); 
    }
}
