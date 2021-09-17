package com.solidprinciple.OpenClosedPrinciple.Controller;

import com.solidprinciple.OpenClosedPrinciple.Invoice.Invoice;

public class SaveToFile extends SaveToDb {

    public SaveToFile(Invoice invoice ){
        super(invoice);
    }

    @Override
    public void save(){
        //Save to file
    }

}