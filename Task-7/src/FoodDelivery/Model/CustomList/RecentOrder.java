package FoodDelivery.Model.CustomList;

import FoodDelivery.Enum.FoodStatus;

public class RecentOrder {

    private long itemId;
    private String restaurantName;
    private FoodStatus orderStatus;
    private long orderID;

    public RecentOrder(long orderID, long itemId, String restaurantName, FoodStatus orderStatus) {
        this.itemId = itemId;
        this.orderID = orderID;
        this.restaurantName = restaurantName;
        this.orderStatus = orderStatus;
    }

    public long getItemId() {
        return itemId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public FoodStatus getOrderStatus() {
        return orderStatus;
    }

    public long getitemId(){
        return itemId;
    }

    public void setOrderStatus(FoodStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public long getOrderID() {
        return orderID;
    }
}
