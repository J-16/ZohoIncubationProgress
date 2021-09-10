package com.solidprinciple.DependencyInversionPrinciple.Controller;

import com.solidprinciple.DependencyInversionPrinciple.Interface.SaveInterface;
import com.solidprinciple.DependencyInversionPrinciple.Invoice.Invoice;

public class SaveToFile implements SaveInterface {

    private final Invoice invoice;

    public SaveToFile(Invoice invoice ){
       this.invoice = invoice;
    }

    @Override
    public void save()  {
        //Save to file
        System.out.println("Saved to file");
    }

}