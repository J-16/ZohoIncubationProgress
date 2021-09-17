package FoodDelivery.Controllers;

import FoodDelivery.CustomComparators.FoodComparator;
import FoodDelivery.Model.CustomList.FoodList;
import FoodDelivery.Model.CustomList.OrderList;
import FoodDelivery.Model.CustomList.RecentOrder;
import FoodDelivery.Database.Database;
import FoodDelivery.Enum.FoodStatus;
import FoodDelivery.Enum.FoodTag;
import FoodDelivery.Model.RestaurantAbstract.Restaurant;
import FoodDelivery.Model.Users.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RestaurantController extends Database{

    private Scanner sc  = new Scanner(System.in);

    public void displayOrderList( Restaurant restaurant ){

        ArrayList<OrderList> orderList = restaurant.getOrderList();
        if(orderList.size() == 0) {
            System.out.println("No orders were made yet");
            return;
        }
        System.out.println("Order ID         |           ItemId           |          Customer Name        |         Customer Address");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for(OrderList ol : orderList)
            System.out.println( ol.getOrderId() + "          " + ol.getItemNum() + "        " + ol.getToUserName() + "         " + ol.getAddress() );
        System.out.println("---------------------------------------------------------------------------------------------------------");
        int i;
        do{
            System.out.println("1. Update order ---- or any other key to go back");
            i = sc.nextInt();
            if (i==1) updateOrderList(orderList);
        }while(i==1);
    }


    public void updateOrderList( Restaurant restaurant ){

        ArrayList<OrderList> orderList = restaurant.getOrderList();
        if(orderList.size() == 0) {
            System.out.println("No orders were made yet");
            return;
        }
        System.out.println("Order ID         |           ItemId           |          Customer Name        |         Customer Address");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for(OrderList ol : orderList)
            System.out.println( ol.getOrderId() + "          " + ol.getItemNum() + "        " + ol.getToUserName() + "         " + ol.getAddress() );
        System.out.println("---------------------------------------------------------------------------------------------------------");

        long c;
        do{
            System.out.println("Enter order Number to update ---- or --- 0 to go back");
            c = sc.nextInt();
            if( updateOrder(c, orderList) ){
                System.out.println("Updated");
            }
            else System.out.println("Enter valid ID");
        }while(c!=0);

    }

    private void updateOrderList( ArrayList<OrderList> orderList ){
        long c;
        do{
            System.out.println("Enter order ID ---- or --- 0 to go back");
            c = sc.nextInt();
            if( updateOrder(c, orderList) ){
                System.out.println("Updated");
            }
            else System.out.println("Enter valid ID");
        }while(c!=0);
    }

    public boolean updateOrder(long c, ArrayList<OrderList> orderList){

        for(OrderList ol : orderList){
            if(ol.getOrderId() == c){
                int i;
                do{
                    System.out.println("1.PREPARING 2.OUT FOR DELIVERY, 0.go back");
                    i = sc.nextInt();
                    switch(i){
                        case 1:
                            ol.setStatus(FoodStatus.PREPARING);
                            return true;
                        case 2:
                            ol.setStatus(FoodStatus.OUTFORDELIVERY);
                            return true;
                        default:
                            System.out.println("Enter a valid option");
                    }
                }while( i!=0 );
            }
        }
        return false;
    }

    public void addFood(Restaurant restaurant){
        String name;
        int itemID;
        float price;
        int quantity;
        int rating = 3;
        boolean availability;
        FoodTag tag;

        System.out.println("Enter food : ");
        name = sc.next();
        System.out.println("Enter food ID : ");
        itemID = sc.nextInt();
        System.out.println("Enter price : ");
        price = sc.nextInt();
        System.out.println("Enter quantity : ");
        quantity = sc.nextInt();
        System.out.println("Enter 1.available 2.not available : ");
        int  i;
        i = sc.nextInt();
        availability = i == 1;
        System.out.println("Enter 1.BreakFast 2.Lunch 3.Dinner : ");
        i = sc.nextInt();
        tag = i == 1 ? FoodTag.BF : i == 2 ? FoodTag.LN : FoodTag.DN;

        restaurant.setFoodList(new FoodList(name,itemID,price,quantity,rating,availability,tag));
    }

    public void updateFood(Restaurant restaurant){
        ArrayList<FoodList> foodList = restaurant.getFoodList();
        System.out.println("Order ID       |       Dish           |          Quantity        |          Availability ");
        System.out.println("-----------------------------------------------------------------------------------------");
        for(FoodList foodlist: foodList)
            System.out.println( foodlist .getItemID() + "                  " +  foodlist.getName() + "                     " + foodlist.getQuantity() + "                       " + foodlist.getAvailability() );
        System.out.println("-----------------------------------------------------------------------------------------");
        int c;
        long itemtId;
        do{
            System.out.println("1.Set Quantity 2.Set Availability 0.To go back");
            c = sc.nextInt();
            System.out.println("Enter Item Id : ");
            itemtId= sc.nextInt();
            switch(c){
                case 1:
                    if (!updateQuantity( foodList, itemtId )) System.out.println("Failed");
                    return;
                case 2:
                    if(!updateAvailability( foodList, itemtId )) System.out.println("Failed");
                    return;
            }
        }while(c!=0 && itemtId!=0);
    }

    private boolean updateQuantity(ArrayList<FoodList> foodlist, long itemtId){
        System.out.println("1.add 2.remove");
        int i = sc.nextInt();
        System.out.println("Enter Quantity");
        int q = sc.nextInt();
        q = i == 1 ? q : (-q);
        for(FoodList fl : foodlist){
            if(itemtId == fl.getItemID() ){
                fl.setQuantity(q);
                setAvailabilityByID(foodlist,itemtId);
                return true;
            }
        }
        return false;
    }

    private void setAvailabilityByID(ArrayList<FoodList> foodlist, long itemId){
        for(FoodList fl : foodlist){
            if(fl.getItemID() == itemId){
                if (fl.getQuantity() == 0)
                    fl.setAvailability(false);
                else
                    fl.setAvailability(true);
            }
        }
    }

    private  boolean updateAvailability( ArrayList<FoodList> foodlist, long itemtId ){
        System.out.println("1.yes 2.no");
        int i = sc.nextInt();
        boolean availability = i == 1;
        for(FoodList fl : foodlist){
            if(itemtId == fl.getItemID() ){
                fl.setAvailability( availability );
                return true;
            }
        }
        return false;
    }

    public void selectRestaurant(Users user){
        int c;
        do{
            System.out.println("Enter restaurant id or 0 to go back");
            displayRestaurant();
            c = sc.nextInt();

            if( c > 0 && c <= restaurantsList.size() ){
                foodInRestaurant(c, user);
            }
            else{
                System.out.println("Enter valid restaurant");
            }

        }while (c != 0);
    }

    public void foodInRestaurant(int c, Users user){
        Object restaurantName =  restaurantsList.keySet().toArray()[c-1];
        Restaurant restaurant = restaurantsList.get( restaurantName );
        displayFood( restaurant,"default");
        int itemNum;
        do{
            System.out.println("Sort food as per 1.price, 2.rating --or to place order-- Enter item Id ---or--- 0 go back ");
            itemNum = sc.nextInt();
            if(itemNum == 1)
                displayFood(restaurant, "price");
            else if (itemNum == 2)
                displayFood(restaurant, "rating");
            else if( isValidItemNumber( restaurant, itemNum) ){
                user.setRecentOrder( restaurant.placeOrder( itemNum, user.getName(), user.getAddress(), user) );
                break;
            }
            else
                System.out.println("Invalid selection");
        }while(itemNum != 0);

    }

    public int searchRestaurant(){
        String restaurantName;
        do{
            System.out.println("Enter restaurant name or \"b\" to go back");
            restaurantName = sc.next();
            int i=0;
            for( Restaurant restaurant : restaurantsList.values() ) {
                ++i;
                if (restaurant.getName().equals(restaurantName)){
                    return i;
                }
            }
        }while(!restaurantName.equals("b"));
        return -1;
    }

    public void displayRestaurant(){
        int i=1;
        System.out.println( "Id    |   Name      |    Location" );
        System.out.println( "----------------------------------" );
        for( Restaurant restaurant : restaurantsList.values() ){
            System.out.println( i++ + "           " + restaurant.getName() + "         " + restaurant.getAddress() );
        }
        System.out.println( "----------------------------------" );
    }

    public boolean isValidItemNumber(Restaurant restaurant , int itemID){
        ArrayList<FoodList> foodList = restaurant.getFoodList();
        for(FoodList fl : foodList){
            if(fl.getItemID()  == itemID)
                return true;
        }
        return false;
    }


    public void trackOrder(Users user){

        ArrayList<RecentOrder> recentOrder = user.getRecentOrder();
        Restaurant restaurant = null;

        for(RecentOrder rs : recentOrder){
            if( rs.getOrderStatus() != FoodStatus.DELIVERED ){
                restaurant = restaurantsList.get( rs.getRestaurantName() );
                restaurant.traceOrder(user.getName());
                break;
            }
        }
        if(restaurant == null) return;
        int i;
        do{
            System.out.println("Select 1. to cancel order or 0 to go back");
            Scanner sc = new Scanner(System.in);
            i = sc.nextInt();
            if (i == 1) {
                System.out.println("Enter item id : ");
                i = sc.nextInt();
                boolean status = restaurant.cancelOrder(user, i);
                if( status ) System.out.println("Successfully your order has been cancelled");
                else System.out.println("Your order cannot be cancelled now");
            } else {
                System.out.println("Invalid option");
            }
        }while( i!=0 );
    }


    public void displayFood(Restaurant restaurant, String foodComparator ){
        ArrayList<FoodList> foodList = restaurant.getFoodList();

        switch(foodComparator){
            case "price":
                foodList.sort(FoodComparator::sortByPrice);
                break;
            case "rating":
                foodList.sort(FoodComparator::sortBYRating);
                break;
            default:
                Collections.sort(foodList);
        }

        System.out.println("ItemId  |  Rating       |     Dish          |          Price           |          Availability");
        System.out.println( "----------------------------------------------------------------------------------------------" );
        for(FoodList fl : foodList){
            if(fl.getQuantity() > 0)
                System.out.println( fl.getItemID() +  "       "  + fl.getRating() + "                 " + fl.getName() +  "                     " + fl.getPrice() + "                          " + (fl.getAvailability() ? "yes" : "no") );
        }
        System.out.println( "----------------------------------------------------------------------------------------------" );
    }

    public void searchFood(){
        System.out.println("not implemented");
    }

}