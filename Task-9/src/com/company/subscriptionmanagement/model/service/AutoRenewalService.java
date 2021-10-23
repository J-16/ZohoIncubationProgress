package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.database.AutoRenewalDB;
import com.company.subscriptionmanagement.database.CurrentDatabase;
import com.company.subscriptionmanagement.database.SubscriptionPlanDB;
import com.company.subscriptionmanagement.database.UserDB;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class AutoRenewalService{

    private PaymentService paymentService;
    private UserDB database;
    private SubscriptionPlanDB subscriptionPlanDB;
    private AutoRenewalDB autoRenewalDB;

    public AutoRenewalService(UserDB database){
        this.database = database;
        this.autoRenewalDB = CurrentDatabase.getAutoRenewalDB();
        this.subscriptionPlanDB = CurrentDatabase.getSubscriptionPlanDBB();
    }

    public void renewal(){
        HashMap<String, Company> companies = database.getCompanies();
        for(Company company : companies.values()){
            HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal = autoRenewalDB.getAutoRenewal();
            LinkedList<CurrentSubscription> currentSubscriptions = autoRenewal.get(LocalDate.now());
            for(CurrentSubscription currentSubscription : currentSubscriptions){
                if(paymentByProduct(currentSubscription)){
                    currentSubscription.setExpireDate(currentSubscription.getExpireDate().plusDays(subscriptionPlanDB.getByID(currentSubscription.getSubscriptionPlanId()).getSubscriptionType().getValue()));
                    new SubscriberService(database.getSubscribersByID(currentSubscription.getSubscriberID()).getAccount().getEmail(),database.getSubscribersByID(currentSubscription.getSubscriberID()).getAccount().getName(),company)
                            .setAutoRenewal(currentSubscription);
                }
                //Todo: auto cancel subscription if payment not done;
            }
        }
    }

    private boolean paymentByProduct(CurrentSubscription currentSubscription){
        paymentService = new CardPaymentService();
        return paymentService.makePayment(currentSubscription.getSubscriberID(), currentSubscription.getPrice());
    }

}