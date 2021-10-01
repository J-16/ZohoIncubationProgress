package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.database.Database;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoRenewalService{

    public void autoRenewal(){
        HashMap<String, Company> companies = Database.getCompanies();
        for(Company company : companies.values()){
            HashMap<LocalDate, ArrayList<CurrentSubscription>> autoRenewal = company.getAutoRenewal();
            ArrayList<CurrentSubscription> currentSubscriptions = autoRenewal.get(LocalDate.now());
            for(CurrentSubscription currentSubscription : currentSubscriptions){
                PaymentByProduct(currentSubscription);
                currentSubscription.setExpireDate(currentSubscription.getExpireDate().plusDays(currentSubscription.getSubscriptionPlan().getSubscriptionType().getValue()));
                new SubscriberService(currentSubscription.getSubscriber().getAccount().getEmail(), currentSubscription.getSubscriber().getAccount().getName(), company)
                        .setAutoRenewal(currentSubscription);
            }
        }
    }

    private void PaymentByProduct(CurrentSubscription currentSubscription){
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment(currentSubscription.getSubscriber().getAccount().getEmail(), currentSubscription.getSubscriptionPlan().getPrice(), currentSubscription.getPaymentDetails());
    }

}
