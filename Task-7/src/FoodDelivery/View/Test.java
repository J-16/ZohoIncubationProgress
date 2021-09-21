package FoodDelivery.View;

import FoodDelivery.Database.Database;
import FoodDelivery.Model.FoodList;
import FoodDelivery.Model.FoodTag;
import FoodDelivery.Model.Restaurant;

public class Test{

    public static void addDetails(){

        Database database = new Database();

        database.registerRestaurant("r1", "r1@r.c", "123",9164782130L,"location1");
        database.registerRestaurant("r2", "r1@r.c", "123",9164782130L,"location2");
        database.registerRestaurant("r3", "r1@r.c", "123",9164782130L,"location3");

        database.registerCustomer("u1", "123",987456213L,"location1");
        database.registerCustomer("u2", "123",987456213, "location1");

        database.registerDeliveryAgent("d1","123","d@d.c",9874562130L);

        Restaurant restaurant = database.getRestaurantByName("r1");
        restaurant.addFood( new FoodList("Chapathi",10001,10,10,5,true, FoodTag.BREAKFAST) );
        restaurant.addFood( new FoodList("Biryani",22001,120,20,5,false,FoodTag.LUNCH) );
        restaurant.addFood( new FoodList("Dosa",30001,15, 50,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("fried rice",40501,80, 10,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("rice",30401,70, 100,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("idly",20001,2, 50,2,false,FoodTag.DINNER) );

        restaurant = database.getRestaurantByName("r2");
        restaurant.addFood( new FoodList("Chapathi",10001,10,10,5,true, FoodTag.BREAKFAST) );
        restaurant.addFood( new FoodList("Biryani",22001,120,20,5,false,FoodTag.LUNCH) );
        restaurant.addFood( new FoodList("Dosa",30001,15, 50,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("fried rice",40501,80, 10,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("rice",30401,70, 100,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("idly",20001,2, 50,2,false,FoodTag.DINNER) );

        restaurant = database.getRestaurantByName("r3");
        restaurant.addFood( new FoodList("Chapathi",10001,10,10,5,true, FoodTag.BREAKFAST) );
        restaurant.addFood( new FoodList("Biryani",22001,120,20,5,false,FoodTag.LUNCH) );
        restaurant.addFood( new FoodList("Dosa",30001,15, 50,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("fried rice",40501,80, 10,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("rice",30401,70, 100,2,true,FoodTag.DINNER) );
        restaurant.addFood( new FoodList("idly",20001,2, 50,2,false,FoodTag.DINNER) );
    }

}
