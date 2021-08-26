class Random implements Cloneable{
  public void method(){
    System.out.println("Hello");
  }
  protected Object clone() throws CloneNotSupportedException{
    return super.clone();
  }
}

public class example{

  public static void main(String[] s) throws Exception{

    Random random = new Random();
    random.method();

    // Class cls = Class.forName("Random");
    // Random randomOne = (Random)cls.newInstance();
    // randomOne.method();

    // Random randomTwo = (Random)random.clone();
    // randomTwo.method();

  }

}