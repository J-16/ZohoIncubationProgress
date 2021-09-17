package FoodDelivery.Model.CustomList;

import FoodDelivery.Enum.FoodTag;

public class FoodList implements Comparable<FoodList>{
    private int itemID;
    private String name;
    private float price;
    private int quantity;
    private int rating;
    private boolean availability;
    private FoodTag tag;

    public FoodList(String name,int itemID, float price, int quantity, int rating, boolean availability, FoodTag tag){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.availability = availability;
        this.rating = rating;
        this.tag = tag;
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRating() {
        return rating;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public FoodTag getTag() {
        return tag;
    }

    public int getItemID(){
        return itemID;
    }

    public void setQuantity(long quantity){
        this.quantity += quantity;
        if(this.quantity < 0) this.quantity=0;
    }

    @Override
    public int compareTo(FoodList o) {
        return this.getAvailability() ? 1 : -1;
    }
}
