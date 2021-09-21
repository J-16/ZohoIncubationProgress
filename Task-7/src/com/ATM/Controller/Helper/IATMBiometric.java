package com.ATM.Controller.Helper;

import com.ATM.Model.Account;

public interface IATMBiometric{
    boolean isValidBiometric(Account account);
}