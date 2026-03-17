import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================================");
        System.out.println("Welcome to the Transport Management System");
        System.out.println("=============================================");

        try {
            //  inputs for Bus
            System.out.print("Enter Bus ID: ");
            String busID = scanner.nextLine();
            System.out.print("Enter Bus Capacity: ");
            int busCapacity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Bus Fuel Type: ");
            String busFuel = scanner.nextLine();
            System.out.print("Enter Bus Route: ");
            String busRoute = scanner.nextLine();
            System.out.print("Does the bus have AC? (true/false): ");
            boolean hasAC = Boolean.parseBoolean(scanner.nextLine());

            Bus bus = new Bus(busID, busCapacity, busFuel, busRoute, hasAC);

            //  inputs for Taxi
            System.out.print("Enter Taxi ID: ");
            String taxiID = scanner.nextLine();
            System.out.print("Enter Taxi Capacity: ");
            int taxiCapacity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Taxi Fuel Type: ");
            String taxiFuel = scanner.nextLine();
            System.out.print("Enter Taxi Driver Name: ");
            String driverName = scanner.nextLine();
            System.out.print("Enter Taxi Base Fare: ");
            double baseFare = Double.parseDouble(scanner.nextLine());

            Taxi taxi = new Taxi(taxiID, taxiCapacity, taxiFuel, driverName, baseFare);

            // inputs for Passenger
            System.out.print("Enter Passenger ID: ");
            String passengerID = scanner.nextLine();
            System.out.print("Enter Passenger Name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Enter Ticket Number: ");
            String ticketNumber = scanner.nextLine();

            Passenger passenger = new Passenger(passengerID, passengerName, ticketNumber);

            // Demonstration
            System.out.println("\n" + "=".repeat(50));
            System.out.println("SYSTEM DEMONSTRATION");
            System.out.println("=".repeat(50));
            
            bus.displayRouteInfo();
            passenger.bookTicket(bus, 15);

            taxi.assignDriver("Jane Smith");
            passenger.bookTicket(taxi, 10);
            taxi.processPayment(100);
            taxi.generateReceipt(100);

            bus.scheduleService();
            bus.performCheckup();

            System.out.println(passenger.getPassengerDetails());

        } catch (NumberFormatException e) {
            System.out.println(" Invalid numeric input. Please enter numbers correctly.");
            System.out.println("Error details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Unexpected error: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
            scanner.close();
            System.out.println("\n Program execution completed safely.");
        }
    }
}
