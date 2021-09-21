package FoodDelivery.Database;

import FoodDelivery.Model.Customer;
import FoodDelivery.Model.DeliveryAgent;
import FoodDelivery.Model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private HashMap<String, Long> restaurants = new HashMap<>();

    private static HashMap<String, Restaurant> restaurantsListDB = new HashMap<>();
    private static HashMap<String, Customer> customerListDB = new HashMap<>();
    private static HashMap<String, DeliveryAgent> deliveryAgentDB = new HashMap<>();

    public ArrayList<String> getAllRestaurant() {
        ArrayList<String> restaurantDetails = new ArrayList<>();
        restaurantsListDB.forEach( (id,restaurant)->{
            restaurantDetails.add(restaurant.toString());
        });
        return restaurantDetails;
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        return restaurantsListDB.get(restaurantName);
    }

    private Restaurant restaurant = null;

    public Restaurant getRestaurantById(long restaurantId) {
        restaurantsListDB.forEach( (id, res)->{
            if(restaurantId == res.getId()){
                restaurant = res;
            }
        });
        return restaurant;
    }

    public boolean isRestaurant(String restaurantName){
        return restaurantsListDB.containsKey(restaurantName);
    }

    public void registerRestaurant(String name, String email, String password, long contactNo, String address) {
        Restaurant restaurant = new Restaurant(name, email, password, contactNo, address);
        Database.restaurantsListDB.put(restaurant.getName(), restaurant);
    }

    public Customer getCustomerByName(String customerName) {
        return customerListDB.get(customerName);
    }

    public boolean isCustomer(String userName){
        return customerListDB.containsKey(userName);
    }

    public void registerCustomer(String name, String password, long contactNo, String address) {
        Customer customer = new Customer(name, password, address, contactNo);
        Database.customerListDB.put(customer.getName(), customer);
    }

    public DeliveryAgent getDeliveryAgent() {
        return null;
    }

    public void registerDeliveryAgent(String name, String email, String password, long contactNo) {
        DeliveryAgent delivery = new DeliveryAgent(name, password, email, contactNo);
        Database.deliveryAgentDB.put(delivery.getName(), delivery);
    }

}