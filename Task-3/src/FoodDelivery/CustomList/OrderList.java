package FoodDelivery.CustomList;

import FoodDelivery.Enum.FoodStatus;

public class OrderList {

    private int itemId;
    private String toUserName;
    private String address;
    private FoodStatus status;
    private long orderId;
    private double amountPaid;

    public OrderList(int itemId, String toUserName, String address, FoodStatus status,long orderId, double amountPaid ){
        this.itemId = itemId;
        this.toUserName = toUserName;
        this.address = address;
        this.status=status;
        this.amountPaid = amountPaid;
        this.orderId = orderId;
    }

    public long getItemNum() {
        return itemId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public String getAddress() {
        return address;
    }

    public FoodStatus getStatus() {
        return status;
    }

    public long getOrderId(){
        return orderId;
    }

    public void setStatus(FoodStatus status){
        this.status=status;
    }

    public double getAmountPaid(){
        return amountPaid;
    }
}
