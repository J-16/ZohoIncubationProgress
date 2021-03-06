package com.company.subscriptionmanagement.model.service;

import com.company.subscriptionmanagement.database.CompanyDatabase;
import com.company.subscriptionmanagement.model.Company;
import com.company.subscriptionmanagement.model.CurrentSubscription;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class AutoRenewalService{

    private PaymentService paymentService;

    public void renewal(){
        HashMap<String, Company> companies = new CompanyDatabase().getCompanies();
        for(Company company : companies.values()){
            HashMap<LocalDate, LinkedList<CurrentSubscription>> autoRenewal = company.getAutoRenewal();
            LinkedList<CurrentSubscription> currentSubscriptions = autoRenewal.get(LocalDate.now());
            for(CurrentSubscription currentSubscription : currentSubscriptions){
                if(paymentByProduct(currentSubscription)){
                    currentSubscription.setExpireDate(currentSubscription.getExpireDate().plusDays(currentSubscription.getSubscriptionPlan().getSubscriptionType().getValue()));
                    new SubscriberService(currentSubscription.getSubscriber().getAccount().getEmail(), currentSubscription.getSubscriber().getAccount().getName(), company, new CompanyDatabase())
                            .setAutoRenewal(currentSubscription);
                }
                //Todo: auto cancel subscription if payment not done;
            }
        }
    }

    private boolean paymentByProduct(CurrentSubscription currentSubscription){
        paymentService = new CardPaymentService();
        return paymentService.makePayment(currentSubscription.getSubscriber().getAccount().getEmail(), currentSubscription.getSubscriptionPlan().getPrice(), currentSubscription.getPaymentDetails());
    }

}