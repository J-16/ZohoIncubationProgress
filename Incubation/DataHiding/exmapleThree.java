class Bank{
  
  private int balance=0;

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

public class exmapleThree {
  
  public static void main(String[] s){

    Bank user1 = new Bank();
    //user1.balance = 100; // not possible
    System.out.println(user1.deposit(100));
    user1.getBalance();

  }

}
