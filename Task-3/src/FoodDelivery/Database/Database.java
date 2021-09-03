package FoodDelivery.Database;

import FoodDelivery.DeliveryAgent.DeliveryAgent;
import FoodDelivery.RestaurantAbstract.Restaurant;
import FoodDelivery.Restaurants.RestaurantOne;
import FoodDelivery.Restaurants.RestaurantThree;
import FoodDelivery.Restaurants.RestaurantTwo;
import FoodDelivery.Users.Users;

import java.util.HashMap;

public class Database {

    protected static HashMap<String, Restaurant> restaurantsList = new HashMap<>();
    protected static HashMap<String, Users> userList = new HashMap<>();
    protected static HashMap<String, DeliveryAgent> deliveryAgent = new HashMap<>();

    {
        restaurantsList.put("r1", new RestaurantOne("r1", "r1@r.c", "123",9164782130L,"location1"));
        restaurantsList.put("r2", new RestaurantTwo("r2", "r1@r.c", "123",9164782130L,"location2"));
        restaurantsList.put("r3", new RestaurantThree("r3", "r1@r.c", "123",9164782130L,"location3"));

        userList.put("u1", new Users("u1", "123", "u@u.c", "location1", 987456213L));
        userList.put("u2", new Users("u2", "123", "u@u.c", "location1", 987456213));

        deliveryAgent.put("d1", new DeliveryAgent("d1","123","d@d.c",9874562130L));
    }

}