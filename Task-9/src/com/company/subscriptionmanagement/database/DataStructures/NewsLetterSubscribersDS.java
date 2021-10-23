package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.NewsLetterSubscribersDB;
import com.company.subscriptionmanagement.model.NewsLetter;

import java.util.ArrayList;

public class NewsLetterSubscribersDS implements NewsLetterSubscribersDB{

    ArrayList<NewsLetter> newsLetterSubscribers;

    public NewsLetterSubscribersDS(){
        newsLetterSubscribers = new ArrayList<>();
    }

    @Override
    public void save(NewsLetter newsLetter) {

    }

    @Override
    public void delete(long subscriberID, long companyID){

    }

    @Override
    public ArrayList<String> getNewsNewsLetterSubscribers() {
        return null;
    }

    @Override
    public boolean getByID(long ID) {
        return false;
    }

    @Override
    public boolean getBySubscriberID(long ID) {
        return false;
    }

    @Override
    public boolean getByCompanyID(long ID) {
        return false;
    }

}