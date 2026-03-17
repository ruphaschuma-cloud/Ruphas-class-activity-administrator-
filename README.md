# Transportation Management System

## Project Description
This Transportation Management System is an Object-Oriented Programming project that simulates a real-world transportation booking platform. The system allows users to:
- Create and manage buses and taxis
- Register passengers
- Book tickets for different vehicles
- Process payments
- Schedule maintenance operations

## Exceptions Implemented

### Custom Exception Classes

1. **VehicleException**
   - Thrown when there are issues with vehicle creation or operation
   - Examples: Invalid vehicle ID format, negative capacity, invalid fuel type

2. **BookingException**
   - Thrown when booking operations fail
   - Examples: Attempting to book on non-operational vehicle, invalid distance

3. **PaymentException**
   - Thrown during payment processing
   - Examples: Negative payment amount, vehicle not operational

4. **InvalidInputException**
   - Thrown for invalid user input
   - Examples: Empty passenger name, invalid ID format

### Exception Handling Examples

```java
// Example 1: Vehicle Creation with Exception Handling
try {
    Bus bus = new Bus("", 50, "Diesel", "Route 12", true);
} catch (VehicleException e) {
    System.out.println("Error: " + e.getMessage());
    // Output: Error: Vehicle ID cannot be null or empty
}

// Example 2: Booking with Exception Handling
try {
    passenger.bookTicket(nonOperationalBus, 15);
} catch (BookingException e) {
    System.out.println("Booking failed: " + e.getMessage());
}

// Example 3: Input Validation
try {
    Passenger passenger = new Passenger("", "Alice", "TK123");
} catch (InvalidInputException e) {
    System.out.println("Invalid input: " + e.getMessage());
}
