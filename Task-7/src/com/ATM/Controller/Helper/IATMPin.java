package com.ATM.Controller.Helper;

import com.ATM.Model.Account;

public interface IATMPin {
    boolean isValidPin(Account account);
}