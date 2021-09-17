package com.ATM.Enum;

public enum MinMaxConstant{
    MIN_TRANSACTION_CHARGE(0.02D),MAX_TRANSACTION_CHARGE(0.04D),
    MIN_BALANCE(100D), MIN_TRANSACTION_ALLOWED(100D);

    private final double value;
    MinMaxConstant(double amount){
        this.value = amount;
    }
    public double getValue(){
        return value;
    }
}