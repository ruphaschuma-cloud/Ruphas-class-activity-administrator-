// Interface: Maintenance
public class Maintenance{
    private string VehicleID;
    private string details;

    public Maintenance(String vehicleID, String details){
        this.vehicleID = vehicleID;
        this.details = details;
    }
    public void scheduleService(){
        System.out.println("Scheduling service for vehicle" + vehicleID +
                            "|Details:"+ details);
    }
}
