
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============================================");
        System.out.println("Welcome to the Transport Management System");
        System.out.println("=============================================");

        // Collections to hold multiple objects of each type
        ArrayList<Bus> buses = new ArrayList<>();
        ArrayList<Taxi> taxis = new ArrayList<>();
        ArrayList<Passenger> passengers = new ArrayList<>();
        ArrayList<Payment> payments = new ArrayList<>();
        ArrayList<Maintenance> maintenances = new ArrayList<>();
        ArrayList<TransportVehicle> transportVehicles = new ArrayList<>();

        try {
            // Input for Bus
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
            buses.add(bus);
            transportVehicles.add(bus);

            // Input for Taxi
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
            taxis.add(taxi);
            transportVehicles.add(taxi);

            // Input for Passenger
            System.out.print("Enter Passenger ID: ");
            String passengerID = scanner.nextLine();
            System.out.print("Enter Passenger Name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Enter Ticket Number: ");
            String ticketNumber = scanner.nextLine();

            Passenger passenger = new Passenger(passengerID, passengerName, ticketNumber);
            passengers.add(passenger);

            // Input for Payment
            System.out.print("Enter Payment Amount: ");
            double paymentAmount = Double.parseDouble(scanner.nextLine());
            Payment payment = new Payment("PAY001", paymentAmount, "Cash");
            payments.add(payment);

            // Input for Maintenance
            System.out.print("Enter Maintenance Details: ");
            String maintenanceDetails = scanner.nextLine();
            Maintenance maintenanceBus = new Maintenance(bus.getVehicleID(), "Bus maintenance: " + maintenanceDetails);
            Maintenance maintenanceTaxi = new Maintenance(taxi.getVehicleID(), "Taxi maintenance: " + maintenanceDetails);
            maintenances.add(maintenanceBus);
            maintenances.add(maintenanceTaxi);

            // Demonstration
            System.out.println("\n" + "=".repeat(50));
            System.out.println("SYSTEM DEMONSTRATION");
            System.out.println("=".repeat(50));

            bus.displayRouteInfo();
            passenger.bookTicket(bus, 15);

            taxi.assignDriver("Tny Mboya");
            passenger.bookTicket(taxi, 10);
            taxi.processPayment(100);
            taxi.generateReceipt(100);

            bus.scheduleService();
            bus.performCheckup();

            System.out.println(passenger.getPassengerDetails());
            System.out.println(taxi.getDetails());
            System.out.println(bus.getDetails());
            System.out.println(transportVehicles.get(0).getDetails());

            // File handling placeholders
            savePassengerData(passengers);
            saveVehicleData(buses, taxis, transportVehicles);
            loadPassengerData();
            loadVehicleData();

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input. Please enter numbers correctly.");
            System.out.println("Error details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            System.out.println("\nProgram execution completed safely.");
        }
    }

    // Save passenger data to file
    private static void savePassengerData(ArrayList<Passenger> passengers) {
        //  implement file writing
    }

    // Save vehicle data to file
    private static void saveVehicleData(ArrayList<Bus> buses, ArrayList<Taxi> taxis, ArrayList<TransportVehicle> transportVehicles) {
        //  implement file writing
    }

    // Load passenger data from file
    private static void loadPassengerData() {
        //  implement file reading
    }

    // Load vehicle data from file
    private static void loadVehicleData() {
        //  implement file reading
    }
}
