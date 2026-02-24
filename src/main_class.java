
// Main Class
public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus("B001", 50, "Diesel", "Route 12", true);
        Taxi taxi = new Taxi("T001", 4, "Petrol", "John Doe", 50);
        Passenger passenger = new Passenger("P001", "Alice", "TK123");

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
