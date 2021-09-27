package FoodDelivery.Controllers;

import FoodDelivery.Model.IPaymentProcessor;
import FoodDelivery.Model.Service.CashOnDelivery;
import FoodDelivery.Model.Service.GpayPaymentProcessor;
import FoodDelivery.Model.Service.PayPalPaymentProcessor;

import java.util.Scanner;

enum PaymentMode {
    CASH_ON_DELIVERY,G_PAY,PAY_PAL;
}

public class PaymentController {
    public IPaymentProcessor getPaymentMethod(double balance){

        int option;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Amount to be payed : " + balance);
            System.out.println("Choose your preferred payment option");
            System.out.println("1. GPay 2. Paypal 3. Cash On Delivery or 0 to quit");
            option = sc.nextInt();

            switch(option){
                case 0 :
                    return null;
                case 1:
                    return new GpayPaymentProcessor();
                case 2:
                    return new PayPalPaymentProcessor();
                case 3:
                    return new CashOnDelivery();
                default:
                    System.out.println("invalid option");
            }
        }while(true);
    }
}
