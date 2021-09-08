package com.DataBase;

import com.Exceptions.UserNotFoundException;
import com.Model.Bank;
import com.Model.BankAgent;
import com.Model.User;
import java.util.HashMap;

public class BankDatabase {
    private static HashMap<Long, Bank> userDb = new HashMap<>();

    public void registerUser(long ATMCardNo, int pin){
        userDb.put(ATMCardNo, new User(ATMCardNo, pin));
    }

    private void registerAgent(int AgentID, int AgentPassword){
        userDb.put((long)AgentID, new BankAgent(AgentID, AgentPassword));
    }

    public Bank getUser(long ATMCardNo){
        if(userDb.containsKey(ATMCardNo))
            return userDb.get(ATMCardNo);
        else throw new UserNotFoundException("User not found");
    }

    public Bank getBankAgent( long agentID ){
        if(userDb.containsKey(agentID))
            return userDb.get(agentID);
        else throw new UserNotFoundException("Agent not found");
    }

}
