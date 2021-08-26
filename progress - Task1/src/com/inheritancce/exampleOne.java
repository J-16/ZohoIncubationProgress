package com.inheritancce;

class BankAccount{

    private String name;
    private int accNo;
    private String address;
    private String email;
    private int count=1010;
    private int balance;
    private int loanAmount;

    BankAccount(String name, String address, String email, int balance){
        if(name == "" || address == "" || email == "" || balance < 0)
            return;
        this.name = name;
        this.address = address;
        this.email = email;
        this.balance = balance;
        accNo = ++count;
    }

    public String getName(){
        return name;
    }

    public int getAccNo(){
        return accNo;
    }

    public int getBalance(){
        return balance;
    }

    protected void setBalance(int balance){
        this.balance += balance;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setLoanAmount(int loanAmount){
        this.loanAmount = loanAmount;
    }

    public int getLoanAmount(){
        return loanAmount;
    }

}

class SavingsAccount extends BankAccount{

    SavingsAccount(String name, String address, String email, int balance){
        super(name,address,email,balance);
    }
    public void withdraw(int amount){
        if(amount < 0)
            return;
        setBalance(-amount);
        System.out.println("Current amount: " + getBalance());
    }

    SavingsAccount(BankAccount bankAccount){
        this(bankAccount.getName(), bankAccount.getAddress(), bankAccount.getEmail(), bankAccount.getBalance());
    }

    public void deposit(){
        if(100 < 0)
            return;
        setBalance(100);
        System.out.println("Successfully deposited \n" +
                "Current Balance : " + getBalance());
    }
}

class LoanAccount extends BankAccount{
    LoanAccount(String name, String address, String email, int balance){
        super(name,address,email,balance);
    }

    LoanAccount(BankAccount bankAccount){
        this(bankAccount.getName(), bankAccount.getAddress(), bankAccount.getEmail(), bankAccount.getBalance());
    }

    public void provideLoan(int amount){
        if(getLoanAmount() > 0)
            return;
        setLoanAmount(amount);
    }

    private void AGRI(int amount){
        if(amount < 0 || amount > 100)
            return;
        provideLoan(amount);
    }
    private void EDU(int amount){
        if(amount < 0 || amount > 50)
            return;
        provideLoan(amount);
    }
    //can have other verification also;
    public void getLoan(String loanType, int amount){
        switch(loanType){
            case "Agri":
                AGRI(amount);
            case "EDU":
                EDU(amount);
        }
    }
    public void payEMI(int amount){
        if(amount<0)
            return;
        setLoanAmount(getLoanAmount() - amount);
    }
    public String toString(){
        return "Pending loan amount of " + getName() + ": " + getLoanAmount();
    }
}

public class exampleOne {
    public static void main(String[] s){

        SavingsAccount user1 = new SavingsAccount("abishek","D.no12", "d@d.com",10);
        user1.deposit();
        user1.withdraw(20);

        LoanAccount user2 = new LoanAccount(user1);
        user2.getLoan("Agri", 50);
        System.out.println(user2);

    }
}