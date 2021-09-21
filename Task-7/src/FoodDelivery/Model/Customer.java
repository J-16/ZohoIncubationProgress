package FoodDelivery.Model;

public class Customer implements IAccount{

    private String name;
    private String password;
    private long contactNumber;
    private String address;

    private final long ID;

    private static long customer_id = 211200310;

    public Customer(String name, String password, String address, long contactNumber){
        this.name = name;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.ID = customer_id++;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public long getId() {
        return ID;
    }

}
