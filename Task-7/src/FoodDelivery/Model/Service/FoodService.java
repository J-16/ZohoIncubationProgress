package FoodDelivery.Model.Service;

import FoodDelivery.Model.FoodList;
import FoodDelivery.Model.Restaurant;

import java.util.ArrayList;

public class FoodService {

    public final void updateFoodQuantity(Restaurant restaurant, long foodID, int quantity){
        for( FoodList foodlist : restaurant.getFoodList() ){
            if( foodID == foodlist.getFoodID() ){
                foodlist.setQuantity( quantity );
                return;
            }
        }
    }

    public boolean updateFoodQuantity(ArrayList<FoodList> foodlist, long itemtId, int add_remove, int quantity){
        quantity = add_remove == 1 ? quantity : (-quantity);
        for(FoodList fl : foodlist){
            if(itemtId == fl.getFoodID() ){
                fl.setQuantity(quantity);
                setAvailabilityByID(foodlist,itemtId);
                return true;
            }
        }
        return false;
    }

    public boolean updateAvailability( ArrayList<FoodList> foodlist, long itemtId, int option){
        boolean availability = option == 1;
        for(FoodList fl : foodlist){
            if(itemtId == fl.getFoodID() ){
                fl.setAvailability( availability );
                return true;
            }
        }
        return false;
    }

    private void setAvailabilityByID(ArrayList<FoodList> foodlist, long itemId){
        for(FoodList fl : foodlist){
            if(fl.getFoodID() == itemId){
                if (fl.getQuantity() == 0)
                    fl.setAvailability(false);
                else
                    fl.setAvailability(true);
            }
        }
    }

}
