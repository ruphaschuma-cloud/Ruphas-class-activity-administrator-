// Concrete Class: Passenger
class Passenger {
    private String passengerID;
    private String name;
    private String ticketNumber;
    private int loyaltyPoints;

    public Passenger(String passengerID, String name, String ticketNumber) 
            throws InvalidInputException {
        validatePassengerInputs(passengerID, name, ticketNumber);
        
        this.passengerID = passengerID;
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.loyaltyPoints = 0;
    }

    private void validatePassengerInputs(String passengerID, String name, String ticketNumber) 
            throws InvalidInputException {
        if (passengerID == null || passengerID.trim().isEmpty()) {
            throw new InvalidInputException("Passenger ID cannot be empty");
        }
        if (!passengerID.matches("[A-Z0-9]{3,10}")) {
            throw new InvalidInputException("Passenger ID must be 3-10 alphanumeric characters");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty");
        }
        if (!name.matches("[A-Za-z\\s-]{2,50}")) {
            throw new InvalidInputException("Name must contain only letters, spaces, or hyphens");
        }
        if (ticketNumber == null || ticketNumber.trim().isEmpty()) {
            throw new InvalidInputException("Ticket number cannot be empty");
        }
    }

    public void bookTicket(TransportVehicle vehicle, double distance) {
        try {
            if (vehicle == null) {
                throw new BookingException("Vehicle cannot be null");
            }
            if (!vehicle.isOperational()) {
                throw new BookingException("Vehicle " + vehicle.getVehicleID() + " is not operational");
            }

            double fare = vehicle.calculateFare(distance);
            int pointsEarned = (int)(fare / 10);
            loyaltyPoints += pointsEarned;
            
            System.out.println("\n" + "=".repeat(40));
            System.out.println("✅ BOOKING CONFIRMED");
            System.out.println("=".repeat(40));
            System.out.println("Passenger: " + name);
            System.out.println("Vehicle: " + vehicle.getDetails());
            System.out.println("Distance: " + distance + " km");
            System.out.printf("Fare: $%.2f\n", fare);
            System.out.println("Loyalty Points Earned: " + pointsEarned);
            System.out.println("Total Loyalty Points: " + loyaltyPoints);
            
        } catch (BookingException | VehicleException e) {
            System.err.println("❌ Booking failed: " + e.getMessage());
        }
    }

    public String getPassengerDetails() {
        try {
            StringBuilder details = new StringBuilder();
            details.append("\n" + "=".repeat(40));
            details.append("\nPASSENGER DETAILS");
            details.append("\n" + "=".repeat(40));
            details.append("\nID: ").append(passengerID);
            details.append("\nName: ").append(name);
            details.append("\nTicket #: ").append(ticketNumber);
            details.append("\nLoyalty Points: ").append(loyaltyPoints);
            details.append("\n" + "=".repeat(40));
            return details.toString();
        } catch (Exception e) {
            return "Error displaying passenger details";
        }
    }
}