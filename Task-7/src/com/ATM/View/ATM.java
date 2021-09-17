package com.ATM.View;

import com.ATM.Controller.ATMController;
import com.ATM.Controller.Helper.ConcreteValidityClass.ATMPinValidity;

public class ATM {

    public void call(){
        ATMController atmController = new ATMController(new ATMPinValidity());
        atmController.ATM();
    }

}