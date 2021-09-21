package FoodDelivery.Controllers;

import FoodDelivery.CustomComparators.FoodComparator;
import FoodDelivery.Database.Database;
import FoodDelivery.Model.*;
import FoodDelivery.Model.Service.OrderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DeliveryController {

    private Scanner SCANNER  = new Scanner(System.in);
    private Customer customer;
    private Database database;
    private OrderService orderService;

    DeliveryController(Customer customer, Database database, OrderService orderService){
        this.customer = customer;
        this.database = database;
        this.orderService = orderService;
    }

    public void selectRestaurant(){
        String restaurantName;
        do{
            System.out.println("Enter restaurant name or q to go back");
            displayRestaurant();
            restaurantName = SCANNER.nextLine();

            if( restaurantName.equals("q") )
                return;

            if( database.isRestaurant(restaurantName) ){
                foodInRestaurant(restaurantName);
                return;
            }
            System.out.println("Enter valid restaurant");

        }while(true);
    }


    public void foodInRestaurant(String restaurantName){

        Restaurant restaurant = database.getRestaurantByName(restaurantName);
        displayFood( restaurant,"default");
        int foodID;
        do{
            System.out.println("Sort food as per 1.price, 2.rating ----- Enter Item Id to place order ---- 0 go back ");
            foodID = SCANNER.nextInt();
            if(foodID == 1)
                displayFood(restaurant, "price");
            else if (foodID == 2)
                displayFood(restaurant, "rating");
            else if( isValidItemNumber( restaurant, foodID) ){
                try{
                    orderService.placeOrder(restaurant, foodID, customer);
                    System.out.println("Your order has been placed");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                return;
            }
            else
                System.out.println("Invalid selection");
        }while(foodID != 0);

    }

    public String searchRestaurant(){
        String restaurantName;
        do{
            System.out.println("Enter restaurant name or \"q\" to go back");
            restaurantName = SCANNER.nextLine();
            if(restaurantName.equals("q"))
                return null;
            if(database.isRestaurant(restaurantName) ) {
                return restaurantName;
            }
            else System.out.println("Not found");
        }while(true);
    }

    public void displayRestaurant(){
        int i=1;
        System.out.println( "Id     |   Name  " );
        System.out.println( "----------------------------------" );
        for( String restaurant : database.getAllRestaurant() ){
            System.out.println( restaurant );
        }
        System.out.println( "----------------------------------" );
    }

    public boolean isValidItemNumber(Restaurant restaurant , int itemID){
        ArrayList<FoodList> foodList = restaurant.getFoodList();
        for(FoodList fl : foodList){
            if(fl.getFoodID()  == itemID)
                return true;
        }
        return false;
    }


    public void trackOrder(){
        ArrayList<OrderDetails> recentOrder;
        try{
            recentOrder = orderService.traceOrder(customer.getId());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Order No    |      FoodId   |      Order Status");
        for(OrderDetails orderdetails : recentOrder){
            int index = 0;
            if( orderdetails.getOrderStatus() != FoodStatus.DELIVERED ){
                System.out.println( index + "        " +  orderdetails.getFoodId() + "            " + orderdetails.getOrderStatus() );
            }
        }

        int i;
        do{
            System.out.println("Select 1. to cancel order or 0 to go back");
            i = SCANNER.nextInt();
            if (i == 1) {
                System.out.println("Enter  Order No : ");
                i = SCANNER.nextInt();
                Restaurant restaurant = database.getRestaurantById( recentOrder.get(i).getRestaurantId() );
                long orderId = recentOrder.get(i).getOrderId();
                boolean status = orderService.cancelOrder(restaurant, customer, orderId);
                if( status ) System.out.println("your order has been cancelled Successfully");
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
                System.out.println( fl.getFoodID() +  "       "  + fl.getRating() + "                 " + fl.getName() +  "                     " + fl.getPrice() + "                          " + (fl.getAvailability() ? "yes" : "no") );
        }
        System.out.println( "----------------------------------------------------------------------------------------------" );
    }

    public void searchFood(){
        System.out.println("not implemented");
    }

}
