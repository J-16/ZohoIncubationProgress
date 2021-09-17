package FoodDelivery.Model.DeliveryAgent;

public class DeliveryAgent {

    private String name;
    private String password;
    private String email;
    private long contactNum;

    public DeliveryAgent(String name, String password, String email, long contactNum) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.contactNum = contactNum;
    }

}