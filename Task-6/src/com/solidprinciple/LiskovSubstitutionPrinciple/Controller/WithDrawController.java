package com.solidprinciple.LiskovSubstitutionPrinciple.Controller;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.LimitedTransactionAccount;

public class WithDrawController {

    private final LimitedTransactionAccount withDrawableAccount;

    public WithDrawController(LimitedTransactionAccount withDrawableAccount){
        this.withDrawableAccount =  withDrawableAccount;
    }

    public void withDraw( double amount ){
        withDrawableAccount.withDraw(amount);
    }

}