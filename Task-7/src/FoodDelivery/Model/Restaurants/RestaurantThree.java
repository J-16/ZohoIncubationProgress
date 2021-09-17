package FoodDelivery.Model.Restaurants;

import FoodDelivery.Model.CustomList.FoodList;
import FoodDelivery.Model.CustomList.RecentOrder;
import FoodDelivery.Enum.FoodTag;
import FoodDelivery.Model.RestaurantAbstract.Restaurant;
import FoodDelivery.Model.Users.Users;

public class RestaurantThree extends Restaurant {

    public RestaurantThree(String name, String email,String password, long contactNo, String address) {
        super(name, email,password, contactNo, address);
        super.setFoodList( new FoodList("Chapathi",10001,10,10,5,true, FoodTag.BF));
        super.setFoodList( new FoodList("Biryani",22001,120,20,5,false,FoodTag.LN));
        super.setFoodList( new FoodList("Dosa",30001,15, 50,2,true,FoodTag.DN));
        super.setFoodList( new FoodList("friderice",40501,80, 10,2,true,FoodTag.DN));
        super.setFoodList( new FoodList("rice",30401,70, 100,2,true,FoodTag.DN));
        super.setFoodList( new FoodList("idly",20001,2, 50,2,false,FoodTag.DN));
    }

    @Override // add offer functionality
    public RecentOrder placeOrder(int itemNum, String name, String address, Users user ) {
        double finalPrice = getAmount( itemNum ) - getOfferPrice();
        if( finalPrice == -1 ) return null;
        return super.placeOrder( itemNum, name, address, user, finalPrice );
    }
}
