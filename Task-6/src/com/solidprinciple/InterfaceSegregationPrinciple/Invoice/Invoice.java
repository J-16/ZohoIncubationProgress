package com.solidprinciple.InterfaceSegregationPrinciple.Invoice;


import com.solidprinciple.InterfaceSegregationPrinciple.Presenter.SaveToDb;
import com.solidprinciple.InterfaceSegregationPrinciple.Interface.Item;

public class Invoice{
    public final static double TAX = 12;

    private final Item item;
    private final int quantity;
    private final double total;

    public Invoice(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.total = calculateTotal();
        new SaveToDb(this).saveInvoice();
    }

    public Item getBook() {
        return item;
    }

    public double getTotal() {
        return total;
    }

    public final double calculateTotal(){
        return (item.getPrice() * this.quantity) + ( this.quantity * TAX);
    }

    public String[] getDetails(){
        return new InvoiceGenerator(this).printInvoice();
    }

}
