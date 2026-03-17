package src.Student.version;

public class Info {
    private String UserName;
    private String UserID;

    public Info(String name, String ID) {
        UserName = UserName;
        this.UserID = UserID;
    }

    public String getName() {
        return UserName;
    }

    public String getID() {
        return UserID;
    }

    public static void main(String[] args) {
        Info info = new Info("Ruphas", "224624");
        System.out.println("Name: " + info.getName());
        System.out.println("ID: " + info.getID());
    }

}
