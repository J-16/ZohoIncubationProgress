package com.solidprinciple.LiskovSubstitutionPrinciple;

import com.solidprinciple.LiskovSubstitutionPrinciple.Model.Current;
import com.solidprinciple.LiskovSubstitutionPrinciple.Model.FixedDeposit;
import com.solidprinciple.LiskovSubstitutionPrinciple.Model.Savings;
import com.solidprinciple.LiskovSubstitutionPrinciple.Controller.DepositController;
import com.solidprinciple.LiskovSubstitutionPrinciple.Controller.WithDrawController;

public class Main {

    public static void main(String ...args){

        Current current = new Current(123654,"u1",100);
        Savings savings = new Savings(412354,"u2",150);
        FixedDeposit fixedDeposit = new FixedDeposit(523654,"u3",100);

        DepositController depositController = new DepositController(current);
        WithDrawController withDrawController = new WithDrawController(current);

        depositController.deposit(100);
        withDrawController.withDraw(50);

        withDrawController= new WithDrawController(savings);
        depositController.deposit(100);
        withDrawController.withDraw(50);

        depositController = new DepositController(savings);
        depositController.deposit(150);

    }

}