package com.solidprinciple.LiskovSubstitutionPrinciple;

import com.solidprinciple.LiskovSubstitutionPrinciple.Controller.TransactionController;
import com.solidprinciple.LiskovSubstitutionPrinciple.Model.Current;
import com.solidprinciple.LiskovSubstitutionPrinciple.Model.Savings;

public class Main {

    public static void main(String ...args){

        Current current = new Current(123654,"u1",1000);
        Savings savings = new Savings(412354,"u2",1500);

        TransactionController transactionController = new TransactionController(current);
        transactionController.transaction(200);
        transactionController.transaction(200);
        transactionController.transaction(200);
        transactionController.transaction(200);

        System.out.println();

        transactionController = new TransactionController(savings);
        transactionController.transaction(200);
        transactionController.transaction(200);
        transactionController.transaction(200);
        transactionController.transaction(200);


    }

}