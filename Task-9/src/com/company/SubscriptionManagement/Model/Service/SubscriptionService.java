package com.company.SubscriptionManagement.Model.Service;

import com.company.SubscriptionManagement.Model.*;

import java.util.HashMap;

public class SubscriptionService {

    private ISubscriber subscriberAccount;

    public SubscriptionService(ISubscriber subscriberAccount){
        this.subscriberAccount = subscriberAccount;
    }

    public void activateTrail(Product product) {
        product.addTrailSubscribers(subscriberAccount.getAccount().getEmail(), 1);
    }

    public void subscribeProduct(Product product, SubscriptionPlan subscriptionPlan){
        product.addProductSubscribers(subscriberAccount.getAccount().getEmail(), new CurrentSubscription(subscriberAccount.getAccount().getEmail(),subscriptionPlan, subscriberAccount.getPaymentDetails()) );
    }

    public void changeSubscription(Product product, SubscriptionPlan newSubscriptionPlan){
        product.getProductSubscribers(subscriberAccount.getAccount().getEmail()).setSubscriptionPlan(newSubscriptionPlan);
    }

    public void pauseSubscription(Product product, int resumeDate){
        CurrentSubscription currentSubscription = getProductSubscriberByEmail(product, subscriberAccount.getAccount().getEmail());
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setPausedDate(2);
        currentSubscription.setResumeSubscriptionDate(resumeDate);
    }

    public void subscribeNewsLetter(String subscriberEmail, Product product){
        product.setNewsLetterSubscribedUsers(subscriberEmail, true);
    }

    public void cancelSubscription(Product product){
        CurrentSubscription currentSubscription = getProductSubscriberByEmail(product, subscriberAccount.getAccount().getEmail());
        currentSubscription.setCurrentlySubscribed(false);
        currentSubscription.setCancelledDated(1);
        currentSubscription.setResumeSubscriptionDate(0);
    }

    public void cancelNewsLetterSubscription(String subscriberEmail, Product product){
        product.setNewsLetterSubscribedUsers(subscriberEmail, false);
    }

    private CurrentSubscription getProductSubscriberByEmail(Product product, String email){
        HashMap<String, CurrentSubscription> productSubscribers = product.getProductSubscribers();
        return productSubscribers.get(email);
    }

    public ISubscriber getSubscriberAccount(){
        return subscriberAccount;
    }
}
