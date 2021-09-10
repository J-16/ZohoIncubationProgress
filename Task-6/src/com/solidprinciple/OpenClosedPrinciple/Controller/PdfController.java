package com.solidprinciple.OpenClosedPrinciple.Controller;

import com.solidprinciple.OpenClosedPrinciple.Invoice.Invoice;

public class PdfController extends DatabaseController {

    public PdfController( Invoice invoice ){
        super(invoice);
    }

    @Override
    public void save()  {
        //Save to file
    }
}