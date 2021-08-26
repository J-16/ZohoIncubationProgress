class Abs{
  
  private int l;
  private int s;

  public static void method(Abs abs){
    System.out.println("Hello" + abs.l + abs.s);
  }

}

public class exmapleTwo {
  
  public static void main(String[] s){
    Abs abs = new Abs();

    Abs.method(abs);
  }

}
