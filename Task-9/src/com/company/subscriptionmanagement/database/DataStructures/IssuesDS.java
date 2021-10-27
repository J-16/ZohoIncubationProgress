package com.company.subscriptionmanagement.database.DataStructures;

import com.company.subscriptionmanagement.database.IssueDB;
import com.company.subscriptionmanagement.model.Issue;

import java.util.ArrayList;

public class IssuesDS implements IssueDB{

    private ArrayList<Issue> issues;

    public IssuesDS(){
        issues = new ArrayList<>();
    }

    @Override
    public void save(Issue issue){
        issues.add(issue);
    }

    @Override
    public void update(Issue updateIssue){
        for(Issue issue : issues){
            if(issue.getID() == updateIssue.getID()){
                issues.remove(issue);
                issues.add(updateIssue);
                return;
            }
        }
    }

    @Override
    public Issue getByID(long ID){
        for(Issue issue : issues){
            if(issue.getID() == ID){
                return issue;
            }
        }
        return null;
    }

    public Issue getByCompanyID(long companyID){
        for(Issue issue : issues){
            if(issue.getCompanyID() == companyID){
                return issue;
            }
        }
        return null;
    }
}
