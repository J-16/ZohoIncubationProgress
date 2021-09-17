package FoodDelivery.Model.RestaurantAbstract;

import FoodDelivery.Model.CustomList.FoodList;
import FoodDelivery.Model.CustomList.OrderList;
import FoodDelivery.Model.CustomList.RecentOrder;
import FoodDelivery.Enum.FoodStatus;
import FoodDelivery.PaymentGateWay.PaymentGateWay;
import FoodDelivery.Model.Users.Users;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Restaurant {

    private final static int CANCELLATION_PENALTY= 15;
    private static long orderID=122112210;
    private static double offerPrice=0;

    private final String name;
    private final String email;
    private final String password;
    private final long contactNo;
    private final String address;
    private double restaurantBalance;
    private static double Balance;

    private final ArrayList<FoodList> foodList;
    private final ArrayList<OrderList> orderList;

    protected Restaurant(String name, String email, String password, long contactNo, String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;
        foodList = new ArrayList<>();
        orderList = new ArrayList<>();
        Balance = 0;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<FoodList> getFoodList(){
        return foodList;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList.add(foodList);
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList.add(orderList);
    }

    public void updateOrderStatus( int orderID, FoodStatus foodStatus ) {
        for(OrderList orders: orderList){
            if( orderID == orders.getOrderId() )
                orders.setStatus(FoodStatus.CANCELLED);
        }
    }

    public int getNumberOfItems() {
        return foodList.size();
    }

    protected final double getAmount(int itermId){
        for( FoodList fl : foodList ){
            if( fl.getItemID() == itermId )
                return fl.getPrice();
        }
            return -1;
    }

    protected final double getOfferPrice(){
        return offerPrice;
    }

    protected final void setOfferPrice(double offerPrice){
        this.offerPrice = offerPrice;
    }

    protected final void setBalance(double balance){
        double commission = balance - 12;
        Restaurant.Balance += commission;
        this.restaurantBalance += balance - commission;
    }

    protected final double getMyBalance(){
        return restaurantBalance;
    }

    protected final void itemUpdate(long itemNum){
        int i=0;
        for( FoodList foodlist : foodList ){
            ++i;
            if( itemNum == foodlist.getItemID() ){
                foodlist.setQuantity( itemNum );
                return;
            }
        }
    }

    public final boolean makePayment(double balance, Users user){
        Scanner sc = new Scanner(System.in);
        PaymentGateWay payment = new PaymentGateWay();
        int option;
        do{
            System.out.println("Amount to be payed : " + balance);
            System.out.println("Choose your preferred payment option");
            System.out.println("1. GPay 2. Paypal 3. CashOnDelivery or 0 to quit");
            option = sc.nextInt();

            switch(option){
                case 0 :
                    return false;
                case 1:
                    payment.GPayPaymentMethod();
                    return true;
                case 2:
                    payment.PayPalPaymentMethod();
                    return true;
                case 3:
                    user.cashOnDelivery();
                    return true;
                default:
                    System.out.println("invalid option");
            }
        }while(true);
    }

    protected final RecentOrder placeOrder(int itemNum, String name, String address, Users user, double finalPrice){
        if( !isAvailable(itemNum) ){
            System.out.println("Not available at the moment ");
            return null;
        }
        if( makePayment( finalPrice, user ) ){
            ++orderID;
            itemUpdate(-itemNum);
            setOrderList( new OrderList( itemNum, name, address, FoodStatus.PLACED, orderID, finalPrice) );
            System.out.println( "Order has been " + FoodStatus.PLACED );
            return new RecentOrder( orderID, itemNum, this.getName(), FoodStatus.PLACED) ;
        }
        System.out.println("Payment failed");
        return null;
    }

    private boolean isAvailable(long itemId){

        for(FoodList fl : foodList ){
            if( fl.getItemID() == itemId){
                return fl.getAvailability();
            }
        }
        return false;
    }

    public final boolean cancelOrder(Users user, int orderID ) {
        ArrayList<OrderList> orderList = this.getOrderList();
        if( orderList == null )return false;
        boolean status = false;
        for( OrderList ol : orderList ){
            if( ol.getOrderId() == orderID && ol.getStatus() != FoodStatus.CANCELLED && ol.getStatus() != FoodStatus.OUTFORDELIVERY ){
                this.updateOrderStatus( orderID, FoodStatus.CANCELLED );
                System.out.println( "Order has been " + ol.getStatus() );
                System.out.println( "Your amount : " + (ol.getAmountPaid() - CANCELLATION_PENALTY) + " will be refunded soon : " );
                status = true;
                itemUpdate( ol.getItemNum() );
                break;
            }
        }
        if(status){
            ArrayList<RecentOrder> recentOrder = user.getRecentOrder();
            if( recentOrder == null) return false;
            int index=-1;
            String restaurantName = null;
            FoodStatus orderStatus = null;
            long itermID = 0;
            for( RecentOrder ro : recentOrder ){
                index++;
                if( ro.getOrderID() == orderID ){
                    restaurantName = ro.getRestaurantName();
                    itermID = ro.getitemId();
                    orderStatus = FoodStatus.CANCELLED;
                    break;
                }
            }
            user.setOrderHistory(itermID,restaurantName, orderID, orderStatus );
            recentOrder.remove( index );
        }
        return status;
    }

    public final void traceOrder(String userName ) {
        ArrayList<OrderList> orderList = this.getOrderList();
        for( OrderList ol : orderList ){
            if( userName.equals(ol.getToUserName()) && !ol.getStatus().equals(FoodStatus.DELIVERED) && !ol.getStatus().equals(FoodStatus.CANCELLED) )
                System.out.println( "order ID:   " + ol.getOrderId() + "  Item Num :   " + ol.getItemNum() + "   Status   " + ol.getStatus() );
        }
    }


    abstract public RecentOrder placeOrder(int ItemID, String toUserName, String address, Users user);
}