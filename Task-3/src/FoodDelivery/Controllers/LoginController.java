package FoodDelivery.Controllers;

import FoodDelivery.Database.Database;
import FoodDelivery.RestaurantAbstract.Restaurant;
import FoodDelivery.Users.Users;
import java.util.Scanner;

public class LoginController extends Database{

    private Scanner sc  = new Scanner(System.in);
    private RestaurantController restaurantController;

    public LoginController(){
        restaurantController = new RestaurantController();
    }

    public void restaurantLoginController(){
        try{

        }
        catch(Exception e){
            System.err.println(e.getCause());
        }
        int c;
        String restaurantName;
        String password;

        do{
            System.out.println("Enter Q to quit");
            System.out.println("Enter Restaurant Name: ");
            restaurantName = sc.nextLine();
            System.out.println("Enter Password :");
            password = sc.nextLine();

            if( restaurantsList.containsKey( restaurantName ) ){
                Restaurant restaurant = restaurantsList.get( restaurantName );

                if( restaurant.getPassword().equals(password) ){

                    do{
                        System.out.println("Select any option : \n"+
                                "1. Order List \n" +
                                "2. Update Order \n" +
                                "3. Add food \n" +
                                "4. Update food \n" +
                                "0. Quit");
                        c = sc.nextInt();

                        switch(c){
                            case 1:
                                restaurantController.displayOrderList( restaurant );
                                break;
                            case 2:
                                restaurantController.updateOrderList( restaurant );
                                break;
                            case 3:
                                restaurantController.addFood( restaurant );
                                break;
                            case 4:
                                restaurantController.updateFood(restaurant);
                                break;
                            default:
                                System.out.println("Enter a valid option or 0 to quit");
                        }

                    }while( c!=0);


                }

                else System.out.println("Enter your restaurantName and password correctly");
            }
            else System.out.println("Enter your restaurantName and password correctly");

        }while( !restaurantName.equals("Q") && !password.equals("Q") );

    }

    public void userLoginController(){

        int c;
        String email;
        String password;

        do{
            System.out.println("Welcome to online food delivery");
            System.out.println("Enter Q to quit");
            System.out.println("Enter email : ");
            email = sc.nextLine();
            System.out.println("Enter Password : ");
            password = sc.nextLine();



            if( userList.containsKey(email) ){
                Users user = userList.get(email);
                if( user.getPassword().equals(password) ){
                    do{
                        System.out.println("Select any option : \n"+
                                "1. Restaurant \n" +
                                "2. Order History \n" +
                                "3. Search restaurant \n" +
                                "4. Track order \n");
                        c = sc.nextInt();
                        switch(c){
                            case 1:
                                restaurantController.selectRestaurant(user);
                                break;
                            case 2:
                                user.displayOrderHistory();
                                break;
                            case 3:
                                int index = restaurantController.searchRestaurant();
                                if( index > 0 )
                                    restaurantController.foodInRestaurant(index, user);
                                else System.out.println("restaurant not found");
                                break;
//                            case 4:
//                                restaurantController.searchFood();
//                                break;
                            case 4:
                                restaurantController.trackOrder(user);
                                break;
                            default:
                                System.out.println("Enter a valid option or 0 to quit");
                        }
                    }
                    while(c!=0);
                }
                else
                    System.out.println("Check your user name and password");
            }
            else {
                System.out.println("Check your user name and password");
            }

        }while( !email.equals("Q") && !password.equals("Q") );
    }

}
