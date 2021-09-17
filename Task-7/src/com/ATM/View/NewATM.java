package com.ATM.View;

import com.ATM.Controller.ATMController;
import com.ATM.Controller.Helper.ConcreteValidityClass.BiometricValidity;

public class NewATM {

    public void call(){
        ATMController atmController = new ATMController(new BiometricValidity());
        atmController.ATM();
    }

}