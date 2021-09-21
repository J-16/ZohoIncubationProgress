package FoodDelivery.Model.Service;

import FoodDelivery.Model.IPaymentProcessor;

public class CashOnDelivery implements IPaymentProcessor {
    @Override
    public void pay() {
        System.out.println("Cash on delivery");
    }
}
