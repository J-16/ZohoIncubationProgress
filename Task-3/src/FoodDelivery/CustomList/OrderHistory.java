package FoodDelivery.CustomList;


import FoodDelivery.Enum.FoodStatus;

public class OrderHistory {
    private long itemId;
    private String restaurantName;
    private FoodStatus orderStatus;
    private long OrderID;

    public OrderHistory(long itemId, String restaurantName, long OrderID, FoodStatus orderStatus){
        this.itemId = itemId;
        this.restaurantName = restaurantName;
        this.OrderID = OrderID;
        this.orderStatus = orderStatus;
    }

    public long getFood() {
        return itemId;
    }

    public FoodStatus getOrderStatus() {
        return orderStatus;
    }

    public long getItemId() {
        return itemId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public long getItemID() {
        return itemId;
    }
}
