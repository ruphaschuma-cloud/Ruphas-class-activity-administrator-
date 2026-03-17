// Concrete Class: Taxi
class Taxi extends TransportVehicle implements Payment {
    private String driverName;
    private double ratePerKm;
    private String licensePlate;

    public Taxi(String vehicleID, int capacity, String fuelType, 
                String driverName, double ratePerKm) throws VehicleException {
        super(vehicleID, capacity, fuelType);
        validateTaxiInputs(driverName, ratePerKm);
        
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.licensePlate = "TEMP-" + vehicleID;
    }

    private void validateTaxiInputs(String driverName, double ratePerKm) throws VehicleException {
        if (driverName == null || driverName.trim().isEmpty()) {
            throw new VehicleException("Driver name cannot be null or empty");
        }
        if (!driverName.matches("[A-Za-z\\s-]{2,50}")) {
            throw new VehicleException("Driver name must contain only letters, spaces, or hyphens");
        }
        if (ratePerKm <= 0 || ratePerKm > 100) {
            throw new VehicleException("Rate per km must be between 0.01 and 100");
        }
    }

    @Override
    public double calculateFare(double distance) throws VehicleException {
        if (distance <= 0) {
            throw new VehicleException("Distance must be positive");
        }
        if (distance > 500) {
            throw new VehicleException("Taxi trips cannot exceed 500 km");
        }
        return distance * ratePerKm;
    }

    public void assignDriver(String newDriver) {
        try {
            if (newDriver == null || newDriver.trim().isEmpty()) {
                throw new VehicleException("Driver name cannot be empty");
            }
            String oldDriver = this.driverName;
            this.driverName = newDriver;
            System.out.println("👤 Driver for taxi " + getVehicleID() + " changed from " + 
                             oldDriver + " to " + newDriver);
        } catch (VehicleException e) {
            System.err.println("Error assigning driver: " + e.getMessage());
        }
    }

    @Override
    public double processPayment(double amount) {
        try {
            if (!isOperational()) {
                throw new PaymentException("Taxi " + getVehicleID() + " is not operational!");
            }
            if (amount <= 0) {
                throw new PaymentException("Payment amount must be positive");
            }

            System.out.println("\n💳 Processing payment for taxi " + getVehicleID());
            System.out.println("Amount: $" + amount);
            
            double driverShare = amount * 0.8;
            double companyShare = amount * 0.2;
            
            System.out.println("Driver (" + driverName + ") receives: $" + driverShare);
            System.out.println("Company receives: $" + companyShare);
            
            return driverShare;
            
        } catch (PaymentException e) {
            System.err.println("Payment error: " + e.getMessage());
            return 0.0;
        }
    }

    @Override
    public void generateReceipt(double amount) {
        try {
            if (amount <= 0) {
                throw new PaymentException("Invalid receipt amount");
            }
            
            System.out.println("\n" + "=".repeat(40));
            System.out.println("TAXI RECEIPT");
            System.out.println("=".repeat(40));
            System.out.println("Taxi ID: " + getVehicleID());
            System.out.println("Driver: " + driverName);
            System.out.println("License Plate: " + licensePlate);
            System.out.println("Amount: $" + amount);
            System.out.println("Date: " + java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("=".repeat(40));
            
        } catch (PaymentException e) {
            System.err.println("Error generating receipt: " + e.getMessage());
        }
    }

    @Override
    public String getDetails() {
        return String.format("Taxi[ID: %s, Driver: %s, Rate: $%.2f/km, Plate: %s]", 
            getVehicleID(), driverName, ratePerKm, licensePlate);
    }
}