package FoodDelivery.Users;

import FoodDelivery.CustomList.OrderHistory;
import FoodDelivery.CustomList.RecentOrder;
import FoodDelivery.Enum.FoodStatus;
import FoodDelivery.RestaurantAbstract.Restaurant;

import java.util.ArrayList;

public class Users {
    private String name;
    private String password;
    private String email;
    private long contactNumber;
    private String address;

    private ArrayList<OrderHistory> orderHistory;
    private ArrayList<RecentOrder> recentOrder;

    public Users(String name, String password, String email, String address, long contactNumber){
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        orderHistory = new ArrayList<>();
        recentOrder = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void cashOnDelivery(){
        System.out.println("Pay while you get");
    }

    public void displayOrderHistory(){
        if(orderHistory.size() == 0) System.out.println("You haven't placed any orders yet");
        for(OrderHistory oh : orderHistory){
            System.out.println( "Order Id:"  + oh.getFood() + " Status : " + oh.getOrderStatus() );
        }
    }

    public void setOrderHistory( long itemId, String restaurantName, long OrderID,FoodStatus orderStatus ) {
        orderHistory.add( new OrderHistory(itemId, restaurantName, OrderID,orderStatus) );
    }

    public void setRecentOrder( RecentOrder recentOrder ) {
        this.recentOrder.add(  recentOrder );
    }

    public ArrayList<RecentOrder> getRecentOrder() {
        return recentOrder;
    }


}