package com.company;

import com.company.subscriptionmanagement.controllers.SubscriberController;

public class temp{

    public static void main(String ...args){
        new AutoAddDetails();

//        ProductView productView = new ProductView(new SubscriberController("u@c.c","123456789","company1"), "company1");
//        productView.displaySubscription("prod1");

        SubscriberController subscriberController = new SubscriberController("u@c.c","u","company1");
        subscriberController.unSubscribeNewsletter();
        subscriberController.getNotification();
    }

}