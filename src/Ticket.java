// Abstract Class: Ticket
abstract class Ticket {
    private String ticketNumber;

    public Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public abstract void printTicket();
}
