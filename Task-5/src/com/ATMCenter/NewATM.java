package com.ATMCenter;

import com.Controller.ATMController;
import com.Controller.Helper.NewATMCheck;
import com.Model.ATM;

public class NewATM extends ATM {

    public NewATM(){
        super();
    }

    public void call(){
        NewATMCheck atmCheck = new NewATMCheck();
        ATMController atmController = new ATMController(atmCheck);
        atmController.ATM();
    }
}
