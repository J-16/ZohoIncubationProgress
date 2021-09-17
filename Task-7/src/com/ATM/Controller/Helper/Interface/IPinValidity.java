package com.ATM.Controller.Helper.Interface;

import com.ATM.Model.ConcreteClass.User;

public interface IPinValidity{
    boolean isValidPin(User user);
}