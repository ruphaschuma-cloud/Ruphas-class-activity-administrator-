// Concrete Class: Passenger
class Passenger {
    private String passengerID;
    private String name;
    private String ticketNumber;

    public Passenger(String passengerID, String name, String ticketNumber) {
        this.passengerID = passengerID;
        this.name = name;
        this.ticketNumber = ticketNumber;
    }

    public void bookTicket(TransportVehicle vehicle, double distance) {
        double fare = vehicle.calculateFare(distance);
        System.out.println(name + " booked ticket on " + vehicle.getDetails() + " | Fare: " + fare);
    }

    public String getPassengerDetails() {
        return "PassengerID: " + passengerID + ", Name: " + name + ", Ticket: " + ticketNumber;
    }
}
