package FoodDelivery.CustomComparators;

import FoodDelivery.Model.FoodList;

public class FoodComparator{
    public static int sortBYRating(FoodList f1, FoodList f2){
        if(f1.getRating() > f2.getRating())
            return 1;
        if(f1.getRating() < f2.getRating())
            return -1;
        else return 0;
    }
    public static int sortByPrice(FoodList f1, FoodList f2){
        if(f1.getPrice() > f2.getPrice())
            return 1;
        if(f1.getPrice() < f2.getPrice())
            return -1;
        else return 0;
    }
}
