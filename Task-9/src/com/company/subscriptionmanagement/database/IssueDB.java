package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Issue;

public interface IssueDB{
    public void save(Issue issue);
    public void update();
    public Issue get();
}
