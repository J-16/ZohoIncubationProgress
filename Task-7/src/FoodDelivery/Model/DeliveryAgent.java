package FoodDelivery.Model;

public class DeliveryAgent implements IAccount{

    private String name;
    private String password;
    private String email;
    private long contactNum;

    private final long ID;

    private static long agent_id = 211200310;

    public DeliveryAgent(String name, String password, String email, long contactNum) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.contactNum = contactNum;
        this.ID = agent_id++;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public long getContactNumber() {
        return contactNum;
    }

    public long getId() {
        return ID;
    }

    public static long getAgent_id() {
        return agent_id;
    }
}