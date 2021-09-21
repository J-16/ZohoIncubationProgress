package FoodDelivery.Model.Service;

import FoodDelivery.Controllers.PaymentController;
import FoodDelivery.Exceptions.FoodException;
import FoodDelivery.Exceptions.PaymentException;
import FoodDelivery.Exceptions.RecentOrderException;
import FoodDelivery.Model.*;

import java.util.*;

public class OrderService{

    private final static int CANCELLATION_PENALTY = 15;
    private static long orderID = 122112210;

    private static HashMap<Long, OrderDetails> recentOrder = new HashMap<>();

    private static ArrayList<OrderDetails> orderHistory = new ArrayList<>();

    FoodService foodService = new FoodService();

    public void placeOrder(Restaurant restaurant, long foodID, Customer customer){
        if( !isAvailable(restaurant, foodID) ){
            throw new FoodException("Food not available");
        }
        double finalPrice = calculatePrice(restaurant, foodID);
        if( makePayment( finalPrice, customer ) ){
            ++orderID;
            updateQuantity(restaurant, foodID,-1);
            recentOrder.put(orderID, new OrderDetails( restaurant.getId(), foodID, customer.getId(), orderID, FoodStatus.PLACED, finalPrice));
        }
        else throw new PaymentException("Payment failed");
    }

    public boolean cancelOrder(Restaurant restaurant, Customer customer, long orderID) {
        if( recentOrder == null || !validOrderID(orderID) )
            throw new RecentOrderException("Invalid orderId");

        OrderDetails orderDetails = recentOrder.get(orderID);

        if(customer.getId() != orderDetails.getCustomerId())
            throw new RecentOrderException("You don't have permission to cancel this order");

        if( !orderDetails.getOrderStatus().equals(FoodStatus.OUTFORDELIVERY) ){
            updateOrderStatus( orderID, FoodStatus.CANCELLED );
            addOrderHistory(orderID);
            updateQuantity( restaurant, orderDetails.getFoodId(),1);
            System.out.println( "Order has been " + FoodStatus.CANCELLED );
            System.out.println( "Your amount : " + ( orderDetails.getAmount() - CANCELLATION_PENALTY) + " will be refunded soon : " );
            return true;
        }
        return false;
    }

    public ArrayList<OrderDetails> traceOrder(long customerId) {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        recentOrder.forEach( (orderId, recentorder ) -> {
                    if( customerId == recentorder.getCustomerId() )
                        orderDetails.add( recentorder );
                });
        if(orderDetails.size() == 0) throw new RecentOrderException("No order found");
        return orderDetails;
    }

    private boolean isAvailable(Restaurant restaurant, long foodID){
        for(FoodList foodList : restaurant.getFoodList()){
            if( foodID == foodList.getFoodID() && foodList.getAvailability() )
                return true;
        }
        return false;
    }

    private boolean validOrderID(long orderID){
        return recentOrder.containsKey(orderID);
    }

    private double calculatePrice(Restaurant restaurant, long foodID){
        for( FoodList foodList : restaurant.getFoodList()){
            if(foodList.getFoodID() == foodID)
                return foodList.getPrice();
        }
        return -1;
    }

    private void updateOrderStatus( long orderID, FoodStatus foodStatus ) {
        recentOrder.get(orderID).setOrderStatus(foodStatus);
    }

    private void addOrderHistory(long orderID){
        orderHistory.add( recentOrder.get(orderID) );
        recentOrder.remove(orderID);
    }

    private void updateQuantity(Restaurant restaurant, long foodID, int quantity){
        foodService.updateFoodQuantity(restaurant, foodID, quantity);
    }

    public boolean makePayment(double balance, Customer customer){
        IPaymentProcessor payment = new PaymentController().getPaymentMethod(balance);
        if(payment == null)
            throw new PaymentException("Payment failed");
        payment.pay();
        return true;
    }

    public ArrayList<OrderDetails> getOrderDetails(Restaurant restaurant){
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        recentOrder.forEach((name, recentorder)->{
            if(restaurant.getId() == recentorder.getRestaurantId())
                orderDetails.add( recentorder );
        });
        return orderDetails;
    }

}
