package com.company.subscriptionmanagement.model;

import java.io.Serializable;
import java.time.LocalDate;

public class CurrentSubscription implements Serializable {

    private boolean isCurrentlySubscribed;
    private final LocalDate FIRST_SUBSCRIBED_DATE;
    private LocalDate cancelledDate;
    private LocalDate pausedDate;
    private LocalDate resumeDate;
    private LocalDate expireDate;
    private final long subscriberID;
    private long subscriptionPlanId;
    private final long companyID;
    private final long ID;
    private double price;
    private final double productID;

    private static long generateID = 0;

    public CurrentSubscription(long subscriberID, long companyID, SubscriptionPlan subscriptionPlan){
        FIRST_SUBSCRIBED_DATE = LocalDate.now();
        this.isCurrentlySubscribed = true;
        this.subscriberID = subscriberID;
        subscriptionPlanId = subscriptionPlan.getID();
        this.ID = generateID++;
        this.companyID = companyID;
        this.price = subscriptionPlan.getPrice();
        productID = subscriptionPlan.getProductID();
        this.expireDate = FIRST_SUBSCRIBED_DATE.plusDays(subscriptionPlan.getSubscriptionType().getValue());
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

    public void setPausedDate(LocalDate pausedDate, SubscriptionPlan subscriptionPlan) {
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

    public long getSubscriptionPlanId(){
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(long subscriptionPlanId){
        this.subscriptionPlanId = subscriptionPlanId;
    }

    public long getSubscriberID() {
        return subscriberID;
    }

    public long getCompanyID() {
        return companyID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getID() {
        return ID;
    }

    public double getProductID() {
        return productID;
    }
}