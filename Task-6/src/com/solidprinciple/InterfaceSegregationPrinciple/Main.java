package com.solidprinciple.InterfaceSegregationPrinciple;

import com.solidprinciple.InterfaceSegregationPrinciple.Model.Magazine;
import com.solidprinciple.InterfaceSegregationPrinciple.Model.NewsPaper;
import com.solidprinciple.InterfaceSegregationPrinciple.Presenter.InvoicePresenter;
import com.solidprinciple.InterfaceSegregationPrinciple.Databse.Database;
import com.solidprinciple.InterfaceSegregationPrinciple.Model.Book;


public class Main implements InvoicePresenter.IInvoicePresenter{

    private static InvoicePresenter invoicePresenter;

    public static void main(String[] args){

        {
            Database.bookDb.put("Deep work", new Book("Deep work","Cal Newport",200));
            Database.bookDb.put("Atomic habits", new Book("Atomic habits","James Clear",250));
            Database.bookDb.put("The Hindu", new NewsPaper("The Hindu",2));
            Database.bookDb.put("Magazine 1", new Magazine("Magazine","publisher1",120));
        }

        new Main().generate();
    }

    public void generate(){
        invoicePresenter = new InvoicePresenter(this);
        invoicePresenter.buyBook("Deep work", 2);
        System.out.println();

        invoicePresenter = new InvoicePresenter(this);
        invoicePresenter.buyBook("The Hindu", 20);
        System.out.println();

        invoicePresenter = new InvoicePresenter(this);
        invoicePresenter.buyBook("Magazine 1", 2);
        System.out.println();
    }

    @Override
    public void update(String[] details) {
        System.out.println("Book name : " + details[0]);
        System.out.println("Price : " + details[1]);
        System.out.println("Tax : " + details[2]);
        System.out.println("Total : " + details[3]);
    }

}
