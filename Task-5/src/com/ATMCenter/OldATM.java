package com.ATMCenter;

import com.Controller.ATMController;
import com.Controller.Helper.ATMCheck;
import com.Model.ATM;

public class OldATM extends ATM {

    public OldATM(){
        super();
    }

    public void call(){
        ATMCheck atmCheck = new ATMCheck();
        ATMController atmController = new ATMController(atmCheck);
        atmController.ATM();
    }

}
