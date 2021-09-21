package FoodDelivery.Model;

import java.util.ArrayList;

public class Restaurant implements IRestaurant{

    private String name;
    private String email;
    private String password;
    private long contactNo;
    private String address;

    private double balance;

    private final long ID;

    private static long restaurantId = 100310;

    private ArrayList<FoodList> foodList = new ArrayList<>();

    public Restaurant(String name, String email, String password, long contactNo, String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;
        this.balance = 0;
        this.ID = Restaurant.restaurantId++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getContactNumber() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword(){
        return password;
    }

    public long getId(){
        return ID;
    }

    public ArrayList<FoodList> getFoodList(){
        return foodList;
    }

    public void addFood(FoodList foodList){
        this.foodList.add(foodList);
    }

    @Override
    public String toString(){
        return ID + "    " + name + "    " ;
    }

}
