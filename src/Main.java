
// Main Class with Dynamic Input
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("🚍 TRANSPORTATION MANAGEMENT SYSTEM 🚖");
        System.out.println("========================================\n");
        
        try {
            // Create objects with user input
            System.out.println("Let's set up the system with initial data:\n");
            
            Bus bus = createBus();
            Taxi taxi = createTaxi();
            Passenger passenger = createPassenger();
            
            // Interactive menu
            boolean running = true;
            while (running) {
                displayMenu();
                int choice = getIntInput("Enter your choice: ");
                
                try {
                    switch (choice) {
                        case 1:
                            bus.displayRouteInfo();
                            break;
                        case 2:
                            handleTaxiOperations(taxi);
                            break;
                        case 3:
                            System.out.println(passenger.getPassengerDetails());
                            break;
                        case 4:
                            handleBooking(bus, taxi, passenger);
                            break;
                        case 5:
                            handlePayment(taxi);
                            break;
                        case 6:
                            handleMaintenance(bus);
                            break;
                        case 7:
                            displaySystemStatus(bus, taxi, passenger);
                            break;
                        case 8:
                            runOriginalDemo(bus, taxi, passenger);
                            break;
                        case 9:
                            System.out.println("\n👋 Thank you for using the system. Goodbye!");
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred: " + e.getMessage());
                }
                
                if (running && choice != 9) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
            
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Bus createBus() {
        System.out.println("\n🚌 CREATE NEW BUS");
        System.out.println("------------------");
        
        while (true) {
            try {
                System.out.print("Enter Bus ID (e.g., B001): ");
                String id = scanner.nextLine();
                
                System.out.print("Enter Capacity (20-80): ");
                int capacity = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Enter Fuel Type (Petrol/Diesel/Electric): ");
                String fuelType = scanner.nextLine();
                
                System.out.print("Enter Route Number: ");
                String route = scanner.nextLine();
                
                System.out.print("Has AC? (yes/no): ");
                boolean hasAC = scanner.nextLine().toLowerCase().startsWith("y");
                
                Bus bus = new Bus(id, capacity, fuelType, route, hasAC);
                System.out.println("✅ Bus created successfully!");
                return bus;
                
            } catch (NumberFormatException e) {
                System.err.println("❌ Invalid number format. Please enter a valid number.");
            } catch (VehicleException e) {
                System.err.println("❌ Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ Unexpected error: " + e.getMessage());
            }
            System.out.println("Please try again.\n");
        }
    }

    private static Taxi createTaxi() {
        System.out.println("\n🚖 CREATE NEW TAXI");
        System.out.println("------------------");
        
        while (true) {
            try {
                System.out.print("Enter Taxi ID (e.g., T001): ");
                String id = scanner.nextLine();
                
                System.out.print("Enter Capacity (1-8): ");
                int capacity = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Enter Fuel Type (Petrol/Diesel/Electric/Hybrid): ");
                String fuelType = scanner.nextLine();
                
                System.out.print("Enter Driver Name: ");
                String driver = scanner.nextLine();
                
                System.out.print("Enter Rate per KM ($): ");
                double rate = Double.parseDouble(scanner.nextLine());
                
                Taxi taxi = new Taxi(id, capacity, fuelType, driver, rate);
                System.out.println("✅ Taxi created successfully!");
                return taxi;
                
            } catch (NumberFormatException e) {
                System.err.println("❌ Invalid number format. Please enter a valid number.");
            } catch (VehicleException e) {
                System.err.println("❌ Error: " + e.getMessage());
            }
            System.out.println("Please try again.\n");
        }
    }

    private static Passenger createPassenger() {
        System.out.println("\n👤 CREATE NEW PASSENGER");
        System.out.println("----------------------");
        
        while (true) {
            try {
                System.out.print("Enter Passenger ID (e.g., P001): ");
                String id = scanner.nextLine();
                
                System.out.print("Enter Passenger Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter Ticket Number: ");
                String ticket = scanner.nextLine();
                
                Passenger passenger = new Passenger(id, name, ticket);
                System.out.println("✅ Passenger created successfully!");
                return passenger;
                
            } catch (InvalidInputException e) {
                System.err.println("❌ Error: " + e.getMessage());
            }
            System.out.println("Please try again.\n");
        }
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Display Bus Information");
        System.out.println("2. Taxi Operations");
        System.out.println("3. Display Passenger Details");
        System.out.println("4. Book a Ticket");
        System.out.println("5. Process Payment");
        System.out.println("6. Maintenance Operations");
        System.out.println("7. System Status");
        System.out.println("8. Run Original Demo");
        System.out.println("9. Exit");
        System.out.println("=".repeat(40));
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("❌ Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("❌ Please enter a valid number.");
            }
        }
    }

    private static void handleTaxiOperations(Taxi taxi) {
        System.out.println("\n🚖 TAXI OPERATIONS");
        System.out.println("1. Assign New Driver");
        System.out.println("2. Display Taxi Details");
        int choice = getIntInput("Enter choice: ");
        
        if (choice == 1) {
            System.out.print("Enter new driver name: ");
            String newDriver = scanner.nextLine();
            taxi.assignDriver(newDriver);
        } else {
            System.out.println(taxi.getDetails());
        }
    }

    private static void handleBooking(Bus bus, Taxi taxi, Passenger passenger) {
        System.out.println("\n📅 BOOK A TICKET");
        System.out.println("1. Book Bus");
        System.out.println("2. Book Taxi");
        int choice = getIntInput("Enter choice: ");
        
        double distance = getDoubleInput("Enter distance (km): ");
        
        if (choice == 1) {
            passenger.bookTicket(bus, distance);
        } else if (choice == 2) {
            passenger.bookTicket(taxi, distance);
        }
    }

    private static void handlePayment(Taxi taxi) {
        System.out.println("\n💳 PROCESS PAYMENT");
        double amount = getDoubleInput("Enter payment amount: $");
        taxi.processPayment(amount);
        taxi.generateReceipt(amount);
    }

    private static void handleMaintenance(Bus bus) {
        System.out.println("\n🔧 MAINTENANCE OPERATIONS");
        System.out.println("1. Schedule Service");
        System.out.println("2. Perform Checkup");
        int choice = getIntInput("Enter choice: ");
        
        if (choice == 1) {
            bus.scheduleService();
        } else {
            bus.performCheckup();
        }
    }

    private static void displaySystemStatus(Bus bus, Taxi taxi, Passenger passenger) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("SYSTEM STATUS");
        System.out.println("=".repeat(40));
        System.out.println(bus.getDetails());
        System.out.println("Status: " + (bus.isOperational() ? "✅ Operational" : "🔧 In Service"));
        System.out.println();
        System.out.println(taxi.getDetails());
        System.out.println("Status: " + (taxi.isOperational() ? "✅ Operational" : "🔧 In Service"));
        System.out.println();
        System.out.println("Passenger: " + passenger.getPassengerDetails());
    }

    private static void runOriginalDemo(Bus bus, Taxi taxi, Passenger passenger) {
        System.out.println("\n📋 RUNNING ORIGINAL DEMO");
        System.out.println("=".repeat(40));
        
        bus.displayRouteInfo();
        passenger.bookTicket(bus, 15);

        taxi.assignDriver("Jane Smith");
        passenger.bookTicket(taxi, 10);
        taxi.processPayment(100);
        taxi.generateReceipt(100);

        bus.scheduleService();
        bus.performCheckup();

        System.out.println(passenger.getPassengerDetails());
    }
}