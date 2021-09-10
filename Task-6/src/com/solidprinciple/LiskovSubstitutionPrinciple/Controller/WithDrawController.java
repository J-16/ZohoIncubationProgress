package com.solidprinciple.LiskovSubstitutionPrinciple.Controller;

import com.solidprinciple.LiskovSubstitutionPrinciple.Abstract.WithDrawableAccount;

public class WithDrawController {

    private final WithDrawableAccount withDrawableAccount;

    public WithDrawController(WithDrawableAccount withDrawableAccount){
        this.withDrawableAccount =  withDrawableAccount;
    }

    public void withDraw( double amount ){
        withDrawableAccount.withDraw(amount);
    }

}