package com.company.SubscriptionManagement.Model;

public class CurrentSubscription {

    private String subscriberEmail;
    private SubscriptionPlan subscriptionPlan;
    private boolean isCurrentlySubscribed;
    private PaymentDetails paymentDetails;
    //TODO: date update
    private final int FIRST_SUBSCRIBED_DATE;
    private int cancelledDated;
    private int pausedDate;
    private int resumeSubscriptionDate;
    //private double HashMap<>; date , amountPaid

    public CurrentSubscription(String subscriberEmail, SubscriptionPlan subscriptionPlan, PaymentDetails paymentDetails){
        this.subscriberEmail = subscriberEmail;
        this.subscriptionPlan = subscriptionPlan;
        FIRST_SUBSCRIBED_DATE = 1;
        this.paymentDetails = paymentDetails;
        isCurrentlySubscribed = true;
    }

    public String getSubscriberEmail(){
        return subscriberEmail;
    }

    public void setSubscriberEmail(String subscriberEmail){
        this.subscriberEmail = subscriberEmail;
    }

    public SubscriptionPlan getSubscriptionPlan(){
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan){
        this.subscriptionPlan = subscriptionPlan;
    }

    public boolean isCurrentlySubscribed(){
        return isCurrentlySubscribed;
    }

    public void setCurrentlySubscribed(boolean currentlySubscribed){
        isCurrentlySubscribed = currentlySubscribed;
    }

    public int getFirstSubscribedDate() {
        return FIRST_SUBSCRIBED_DATE;
    }

    public int getCancelledDated() {
        return cancelledDated;
    }

    public void setCancelledDated(int cancelledDated){
        this.cancelledDated = cancelledDated;
    }

    public int getPausedDate(){
        return pausedDate;
    }

    public void setPausedDate(int pausedDate) {
        this.pausedDate = pausedDate;
    }

    public int getResumeSubscriptionDate() {
        return resumeSubscriptionDate;
    }

    public void setResumeSubscriptionDate(int resumeSubscriptionDate) {
        this.resumeSubscriptionDate = resumeSubscriptionDate;
    }
}
