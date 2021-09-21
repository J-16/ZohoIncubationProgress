package FoodDelivery.Model.Service;

import FoodDelivery.Model.IPaymentProcessor;

public class PayPalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void pay() {
        System.out.println("Payment done via paypal");
    }
}