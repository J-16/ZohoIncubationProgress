package com.solidprinciple.InterfaceSegregationPrinciple.Presenter;

import com.solidprinciple.InterfaceSegregationPrinciple.Databse.Database;
import com.solidprinciple.InterfaceSegregationPrinciple.Invoice.Invoice;

public class InvoicePresenter {

    private IInvoicePresenter iInvoicePresenter;
    private String[] details;

    public InvoicePresenter(IInvoicePresenter iInvoicePresenter){
        this.iInvoicePresenter = iInvoicePresenter;
    }

    public void buyBook(String bookName, int quantity){
        if(quantity <=0 ){
            throw new RuntimeException("invalid quantity");
        }
        Invoice invoice = new Invoice(Database.bookDb.get(bookName), quantity);
        details = invoice.getDetails();
        iInvoicePresenter.update(details);
    }

    public interface IInvoicePresenter{
        void update(String[] details);
    }

}