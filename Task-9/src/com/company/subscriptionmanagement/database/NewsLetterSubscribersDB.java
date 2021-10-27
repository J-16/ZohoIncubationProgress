package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.NewsLetter;

import java.util.ArrayList;

public interface NewsLetterSubscribersDB{
    void save(NewsLetter newsLetter);
    void delete(long subscriberID, long companyID, long productID);
    ArrayList<NewsLetter> getNewsNewsLetterSubscribers();
    boolean getByID(long ID);
    boolean getBySubscriberID(long subscriberID);
    boolean getByCompanyID(long companyID);
    boolean getByProductID(long companyID, long productID, long subscriberID);
}
