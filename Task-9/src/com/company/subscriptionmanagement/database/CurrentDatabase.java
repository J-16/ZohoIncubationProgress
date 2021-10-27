package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.database.DataStructures.*;

public class CurrentDatabase{

    //TODO : draw db for these

    private static final UserDB database = new UserDS();
    private static final AutoRenewalDB autoRenewalDB = new AutoRenewalDS();
    private static final CouponDB couponDB = new CouponDS();
    private static final PaymentDetailsDB paymentDetailsDB = new PaymentDetailsDS();
    private static final IssueDB issueDB = new IssuesDS();
    private static final ProductsDB productsDB = new ProductsDS();
    private static final SubscriptionPlanDB subscriptionPlanDB = new SubscriptionPlansDS();
    private static final NewsLetterSubscribersDB newsLetterSubscribersDB = new NewsLetterSubscribersDS();
    private static final TrailSubscribersDB trailSubscribersDB = new TrailSubscribersDS();
    private static final ProductSubscribersDB productSubscribersDB = new ProductSubscribersDS();
    private static final NotificationDB notificationDB = new NotificationDS();

    private CurrentDatabase(){

    }

    public static UserDB getUserDatabase(){
        return database;
    }

    public static AutoRenewalDB getAutoRenewalDB(){
        return autoRenewalDB;
    }

    public static CouponDB getCouponDB(){
        return couponDB;
    }

    public static PaymentDetailsDB getPaymentDetailsDB(){
        return paymentDetailsDB;
    }

    public static IssueDB getIssueDB(){
        return issueDB;
    }

    public static ProductsDB getProductsDB(){
        return productsDB;
    }

    public static SubscriptionPlanDB getSubscriptionPlanDBB(){
        return subscriptionPlanDB;
    }

    public static NewsLetterSubscribersDB getNewsLetterSubscribersDB(){
        return newsLetterSubscribersDB;
    }

    public static TrailSubscribersDB getTrailSubscribersDB(){
        return trailSubscribersDB;
    }

    public static ProductSubscribersDB getProductSubscribersDB(){
        return productSubscribersDB;
    }

    public static NotificationDB getNotificationDB() {
        return notificationDB;
    }
}