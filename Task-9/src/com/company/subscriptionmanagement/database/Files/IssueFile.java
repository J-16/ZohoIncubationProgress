package com.company.subscriptionmanagement.database.Files;

import com.company.subscriptionmanagement.database.IssueDB;
import com.company.subscriptionmanagement.model.Issue;

import java.io.File;

public class IssueFile implements IssueDB {

    private File issuesFile = new File("issues.csv");

    public void save(Issue issue){

    }

    public void update(){

    }

    public Issue get(){
        return null;
    }

}
