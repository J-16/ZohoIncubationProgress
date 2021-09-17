package FoodDelivery.View;

import FoodDelivery.Controllers.LoginController;
import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        try{
            int c =0;
            LoginController loginController = new LoginController();
            do{
                System.out.println("Select 1.Restaurant Login   2.User Login   0.Quit");
                c = sc.nextInt();
                switch(c){
                    case 0:
                        return;
                    case 1:
                        loginController.restaurantLoginController();
                        break;
                    case 2:
                        loginController.userLoginController();
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
            }while(true);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
