package FoodDelivery.Model;

public class FoodList implements Comparable<FoodList>{

    private int foodID;
    private String name;
    private double price;
    private int quantity;
    private int rating;
    private boolean availability;
    private FoodTag tag;

    public FoodList(String name,int foodID, double price, int quantity, int rating, boolean availability, FoodTag tag){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.availability = availability;
        this.rating = rating;
        this.tag = tag;
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
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

    public int getFoodID(){
        return foodID;
    }

    public void setQuantity(long quantity){
        if(this.quantity < 0)
            return;
        this.quantity += quantity;
    }

    @Override
    public int compareTo(FoodList o) {
        return this.getName().compareTo(o.getName());
    }

}
