package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.NewsLetter;

import java.util.ArrayList;

public interface NewsLetterSubscribersDB{
    public void save(NewsLetter newsLetter);
    public void delete(long subscriberID, long companyID);
    public ArrayList<String> getNewsNewsLetterSubscribers();
    public boolean getByID(long ID);
    public boolean getBySubscriberID(long ID);
    public boolean getByCompanyID(long ID);
}
