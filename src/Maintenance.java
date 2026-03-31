public class Maintenance {
    private String vehicleID;
    private String details;

    public Maintenance(String vehicleID, String details) {
        this.vehicleID = vehicleID;
        this.details = details;
    }

    public void scheduleService() {
        System.out.println("Scheduling service for Vehicle " + vehicleID +
                           " | Details: " + details);
    }

    public void performCheckup() {
        System.out.println("Performing checkup for Vehicle " + vehicleID +
                           " | Details: " + details);
    }
}
