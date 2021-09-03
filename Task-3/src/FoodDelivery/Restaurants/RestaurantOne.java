package FoodDelivery.Restaurants;

import FoodDelivery.CustomList.RecentOrder;
import FoodDelivery.Enum.FoodTag;
import FoodDelivery.CustomList.FoodList;
import FoodDelivery.OffersInterface.offers;
import FoodDelivery.RestaurantAbstract.Restaurant;
import FoodDelivery.Users.Users;

public class RestaurantOne extends Restaurant implements offers {

    public RestaurantOne(String name, String email,String password, long contactNo, String address) {
        super(name, email,password, contactNo, address);
        super.setFoodList( new FoodList("Chapathi",10001,10,10,5,true, FoodTag.BF));
        super.setFoodList( new FoodList("Biryani",22001,120,20,5,false,FoodTag.LN));
        super.setFoodList( new FoodList("Dosa",30001,15, 50,2,true,FoodTag.DN));
        super.setFoodList( new FoodList("fried rice",40501,80, 10,2,true,FoodTag.DN));
        super.setFoodList( new FoodList("rice",30401,70, 100,2,true,FoodTag.DN));
        super.setFoodList( new FoodList("idly",20001,2, 50,2,false,FoodTag.DN));
    }

    @Override // add offer functionality
    public RecentOrder placeOrder( int itemNum, String name, String address, Users user ) {
        customOffer();
        double finalPrice = getAmount( itemNum ) - super.getOfferPrice();
        if( finalPrice == -1 ) return null;
        return super.placeOrder( itemNum, name, address, user, finalPrice );
    }

    @Override
    public void festivalOffer() {
        super.setOfferPrice( 10 );
    }

    @Override
    public void customOffer() {
        super.setOfferPrice( 5 );
    }

}
