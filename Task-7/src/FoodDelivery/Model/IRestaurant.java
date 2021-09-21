package FoodDelivery.Model;

import java.util.ArrayList;

public interface IRestaurant extends IAccount{
    ArrayList<FoodList> getFoodList();
    void addFood(FoodList foodList);
}
