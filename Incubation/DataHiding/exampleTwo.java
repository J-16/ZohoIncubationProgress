abstract class bankMethod{
  public abstract int withdraw(int amount);
  public abstract int deposit(int amount);
}

class Bank extends bankMethod{
  
  public int balance=0;

  public int withdraw(int amount){
    if(amount > 0){
      balance -= amount;
      return balance;
    }
    System.out.println("Invalid Amount");
    return balance;
  }
  public int deposit(int amount){
    if(amount > 0){
      balance += amount;
      return balance;
    }
    System.out.println("Invalid Amount");
    return balance;
  }
  
  public int getBalance(){
    return balance;
  }
}

public class exampleTwo {
  
  public static void main(String[] s){

    bankMethod user1 = new Bank();
    //user1.balance = 100; // not possible
    System.out.println(user1.deposit(100));
    //user1.getBalance(); // not possible

  }

}
