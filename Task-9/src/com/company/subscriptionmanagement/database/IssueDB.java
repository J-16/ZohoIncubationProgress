package com.company.subscriptionmanagement.database;

import com.company.subscriptionmanagement.model.Issue;

public interface IssueDB{
    public void save(Issue issue);
    public void update(Issue updateIssue);
    public Issue getByID(long ID);
    public Issue getByCompanyID(long companyID);
}
