package com.company.subscriptionmanagement.model;

import java.time.LocalDate;

public class CurrentSubscription {

    private Subscriber subscriber;
    private SubscriptionPlan subscriptionPlan;
    private boolean isCurrentlySubscribed;
    private PaymentDetails paymentDetails;
    private final LocalDate FIRST_SUBSCRIBED_DATE;
    private LocalDate cancelledDate;
    private LocalDate pausedDate;
    private LocalDate resumeDate;
    private LocalDate expireDate;

    public CurrentSubscription(Subscriber subscriberEmail, SubscriptionPlan subscriptionPlan, PaymentDetails paymentDetails){
        this.subscriber = subscriberEmail;
        this.subscriptionPlan = subscriptionPlan;
        FIRST_SUBSCRIBED_DATE = LocalDate.now();
        this.paymentDetails = paymentDetails;
        isCurrentlySubscribed = true;
        this.expireDate = FIRST_SUBSCRIBED_DATE.plusDays(subscriptionPlan.getSubscriptionType().getValue());
    }

    public Subscriber getSubscriber(){
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber){
        this.subscriber = subscriber;
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

    public LocalDate getFirstSubscribedDate() {
        return FIRST_SUBSCRIBED_DATE;
    }

    public LocalDate getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(LocalDate cancelledDate){
        this.cancelledDate = cancelledDate;
        this.expireDate = cancelledDate;
    }

    public LocalDate getPausedDate(){
        return pausedDate;
    }

    public void setPausedDate(LocalDate pausedDate) {
        this.pausedDate = pausedDate;
        this.expireDate = pausedDate.plusDays(subscriptionPlan.getSubscriptionType().getValue());
    }

    public LocalDate getResumeSubscriptionDate() {
        return resumeDate;
    }

    public void setResumeSubscriptionDate(LocalDate resumeDate) {
        this.resumeDate = resumeDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
}
