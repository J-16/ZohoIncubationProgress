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
        newsLetterSubscribers.add(newsLetter);
    }

    @Override
    public void delete(long subscriberID, long companyID, long productID){
        for(NewsLetter newsLetter : newsLetterSubscribers){
            if(newsLetter.getCompanyID() == companyID && newsLetter.getSubscribersID() == subscriberID && newsLetter.getProductID() == productID)
                newsLetterSubscribers.remove(newsLetter);
        }
    }

    @Override
    public ArrayList<NewsLetter> getNewsNewsLetterSubscribers() {
        return newsLetterSubscribers;
    }

    @Override
    public boolean getByID(long ID) {
        for(NewsLetter newsLetter : newsLetterSubscribers){
            if(newsLetter.getID() == ID)
                return true;
        }
        return false;
    }

    @Override
    public boolean getBySubscriberID(long subscriberID) {
        for(NewsLetter newsLetter : newsLetterSubscribers){
            if(newsLetter.getSubscribersID() == subscriberID)
                return true;
        }
        return false;
    }

    @Override
    public boolean getByCompanyID(long companyID) {
        for(NewsLetter newsLetter : newsLetterSubscribers){
            if(newsLetter.getCompanyID() == companyID)
                return true;
        }
        return false;
    }

    @Override
    public boolean getByProductID(long companyID, long productID, long subscriberID) {
        for(NewsLetter newsLetter : newsLetterSubscribers){
            if(newsLetter.getCompanyID() == companyID && newsLetter.getSubscribersID() == subscriberID && newsLetter.getProductID() == productID ){
                return true;
            }
        }
        return false;
    }

}