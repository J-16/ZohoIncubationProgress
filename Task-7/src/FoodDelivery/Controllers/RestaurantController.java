package FoodDelivery.Controllers;

import FoodDelivery.Model.*;
import FoodDelivery.Database.Database;
import FoodDelivery.Model.FoodStatus;
import FoodDelivery.Model.FoodTag;
import FoodDelivery.Model.Service.FoodService;
import FoodDelivery.Model.Service.OrderService;

import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantController extends Database{

    private final Scanner SCANNER = new Scanner(System.in);
    private final Restaurant RESTAURANT;
    OrderService orderService;

    FoodService foodService = new FoodService();

    RestaurantController(Restaurant restaurant, OrderService orderService){
        this.RESTAURANT = restaurant;
        this.orderService = orderService;
    }

    public void displayOrders(){

        ArrayList<OrderDetails> orderDetails = orderService.getOrderDetails(RESTAURANT);

        if(orderDetails.size() == 0) {
            System.out.println("No orders were made yet");
            return;
        }
        System.out.println("Order ID         |           ItemId           ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for(OrderDetails orderdetails : orderDetails)
            System.out.println( orderdetails.getOrderId() + "          " + orderdetails.getFoodId() + "        " );
        System.out.println("---------------------------------------------------------------------------------------------------------");
        int orderId;
        do{
            System.out.println("1. Update order ---- or any other key to go back");
            orderId = SCANNER.nextInt();
            if (orderId==1) updateOrder(orderDetails);
        }while(orderId==1);

    }


    public void updateOrder(){
        ArrayList<OrderDetails> orderDetails = orderService.getOrderDetails(RESTAURANT);
        if(orderDetails.size() == 0) {
            System.out.println("No orders were made yet");
            return;
        }
        System.out.println("Order ID         |           ItemId           ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for(OrderDetails orderdetails : orderDetails)
            System.out.println( orderdetails.getOrderId() + "          " + orderdetails.getFoodId() + "        " );
        System.out.println("---------------------------------------------------------------------------------------------------------");

        long orderId;
        do{
            System.out.println("Enter order Number to update ---- or --- 0 to go back");
            orderId = SCANNER.nextInt();
            if( update(orderId, orderDetails) ){
                System.out.println("Updated");
            }
            else System.out.println("Enter valid ID");
        }while(orderId!=0);
    }

    private void updateOrder(ArrayList<OrderDetails> orderDetails){
        long orderId;
        do{
            System.out.println("Enter order ID ---- or --- 0 to go back");
            orderId = SCANNER.nextInt();
            if( update(orderId, orderDetails) ){
                System.out.println("Updated");
            }
            else System.out.println("Enter valid ID");
        }while(orderId!=0);
    }

    private boolean update(long orderId, ArrayList<OrderDetails> orderDetails){
        for(OrderDetails orderdetails : orderDetails){
            if(orderdetails.getOrderId() == orderId){
                int i;
                do{
                    System.out.println("1.PREPARING 2.OUT FOR DELIVERY, 0.go back");
                    i = SCANNER.nextInt();
                    switch(i){
                        case 1:
                            orderdetails.setOrderStatus(FoodStatus.PREPARING);
                            return true;
                        case 2:
                            orderdetails.setOrderStatus(FoodStatus.OUTFORDELIVERY);
                            return true;
                        default:
                            System.out.println("Enter a valid option");
                    }
                }while( i!=0 );
            }
        }
        return false;
    }

    public void addFoodById(){
        String name;
        int itemID;
        float price;
        int quantity;
        int rating = 3;
        boolean availability;
        FoodTag tag;

        System.out.println("Enter dish : ");
        name = SCANNER.next();
        System.out.println("Enter food ID : ");
        itemID = SCANNER.nextInt();
        System.out.println("Enter price : ");
        price = SCANNER.nextInt();
        System.out.println("Enter quantity : ");
        quantity = SCANNER.nextInt();
        System.out.println("Enter 1.available 2.not available : ");
        int  i;
        i = SCANNER.nextInt();
        availability = i == 1;
        System.out.println("Enter 1.BreakFast 2.Lunch 3.Dinner : ");
        i = SCANNER.nextInt();
        tag = i == 1 ? FoodTag.BREAKFAST : i == 2 ? FoodTag.LUNCH : FoodTag.DINNER;

        RESTAURANT.addFood(new FoodList(name,itemID,price,quantity,rating,availability,tag));
    }

    public void updateFood(){
        ArrayList<FoodList> foodList = RESTAURANT.getFoodList();
        System.out.println("Id       |       Dish           |          Quantity        |          Availability ");
        System.out.println("-----------------------------------------------------------------------------------------");
        for(FoodList foodlist: foodList)
            System.out.println( foodlist .getFoodID() + "       " +  foodlist.getName() + "                     " + foodlist.getQuantity() + "                       " + foodlist.getAvailability() );
        System.out.println("-----------------------------------------------------------------------------------------");
        int c;
        long itemtId;
        do{
            System.out.println("1.Set Quantity 2.Set Availability 0.To go back");
            c = SCANNER.nextInt();
            System.out.println("Enter Item Id : ");
            itemtId= SCANNER.nextInt();
            switch(c){
                case 1:
                    System.out.println("1.add 2.remove");
                    int add_remove = SCANNER.nextInt();
                    System.out.println("Enter Quantity");
                    int quantity = SCANNER.nextInt();
                    if (!foodService.updateFoodQuantity(foodList, itemtId,add_remove,quantity))
                        System.out.println("Failed");
                    return;
                case 2:
                    System.out.println("1.yes 2.no");
                    int option = SCANNER.nextInt();
                    if(!foodService.updateAvailability(foodList, itemtId, option ))
                        System.out.println("Failed");
                    return;
            }
        }while(c!=0 && itemtId!=0);
    }


}