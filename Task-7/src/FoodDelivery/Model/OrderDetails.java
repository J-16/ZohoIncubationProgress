package FoodDelivery.Model;

public class OrderDetails{

    private final long RESTAURANT_ID;
    private final long FOOD_ID;
    private final long CUSTOMER_ID;
    private final double AMOUNT;
    private long DELIVERY_AGENT_ID;
    private final long ORDER_ID;
    //private final long PAYMENT_TYPE;

    private FoodStatus orderStatus;

    public OrderDetails(long RESTAURANT_ID, long FOOD_ID, long CUSTOMER_ID, long ORDER_ID, FoodStatus orderStatus, double AMOUNT) {
        this.RESTAURANT_ID = RESTAURANT_ID;
        this.FOOD_ID = FOOD_ID;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.ORDER_ID = ORDER_ID;
        this.AMOUNT = AMOUNT;

        this.orderStatus = orderStatus;
    }

    public long getRestaurantId(){
        return RESTAURANT_ID;
    }

    public long getFoodId(){
        return FOOD_ID;
    }

    public double getAmount() {
        return AMOUNT;
    }

    public long getCustomerId(){
        return CUSTOMER_ID;
    }

    public long getDeliveryAgentId(){
        return DELIVERY_AGENT_ID;
    }

    public void setDeliveryAgentId(long DELIVERY_AGENT_ID){
        this.DELIVERY_AGENT_ID = DELIVERY_AGENT_ID;
    }

    public long getOrderId(){
        return ORDER_ID;
    }

    public void setOrderStatus(FoodStatus foodStatus){
        orderStatus = foodStatus;
    }

    public FoodStatus getOrderStatus(){
        return orderStatus;
    }

}
