package com.ATM.Model;

import java.io.Serializable;

public class CustomerInfo implements Serializable {
    private String name;
    private long ATMNumber;
    private long pin;

    public CustomerInfo(String name, long ATMNumber, long pin){
        this.name = name;
        this.ATMNumber = ATMNumber;
        this.pin = pin;
    }

    public String getName(){
        return name;
    }
    public long getATMNumber(){
        return ATMNumber;
    }
    public long getPin(){
        return pin;
    }

    @Override
    public String toString(){
        return name + " " + ATMNumber + " " + pin;
    }

}
