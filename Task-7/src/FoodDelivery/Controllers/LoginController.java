package FoodDelivery.Controllers;

import FoodDelivery.Database.Database;
import FoodDelivery.Model.Customer;
import FoodDelivery.Model.Restaurant;
import FoodDelivery.Model.Service.OrderService;

import java.util.Scanner;

public class LoginController extends Database{

    private Scanner sc  = new Scanner(System.in);
    private RestaurantController restaurantController;
    private Database database = new Database();
    private OrderService orderService = new OrderService();
    private DeliveryController deliveryController;

    public void restaurantLoginController(){
        int c;
        String restaurantName;
        String password;

        do{
            System.out.println("Enter Q to quit");
            System.out.println("Enter Restaurant Name: ");
            restaurantName = sc.nextLine();
            System.out.println("Enter Password :");
            password = sc.nextLine();

            if( database.isRestaurant( restaurantName ) ){

                Restaurant restaurant = database.getRestaurantByName( restaurantName );
                restaurantController = new RestaurantController(restaurant, orderService);

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
                                restaurantController.displayOrders();
                                break;
                            case 2:
                                restaurantController.updateOrder();
                                break;
                            case 3:
                                restaurantController.addFoodById();
                                break;
                            case 4:
                                restaurantController.updateFood();
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
        String username;
        String password;

        do{
            System.out.println("Welcome to online food delivery");
            System.out.println("Enter Q to quit");
            System.out.println("Enter Username : ");
            username = sc.nextLine();
            System.out.println("Enter Password : ");
            password = sc.nextLine();

            if( database.isCustomer(username) ){
                Customer customer = database.getCustomerByName(username);
                deliveryController = new DeliveryController(customer, database, orderService);
                if( customer.getPassword().equals(password) ){
                    do{
                        System.out.println("Select any option : \n"+
                                "1. Restaurant \n" +
                                "2. Order History \n" +
                                "3. Search restaurant \n" +
                                "4. Track order \n");
                        c = sc.nextInt();
                        switch(c){
                            case 1:
                                deliveryController.selectRestaurant();
                                break;
                            case 2:
                                System.out.println("Not implemented yet");
                                //customer.displayOrderHistory();
                                break;
                            case 3:
                                String restaurantName = deliveryController.searchRestaurant();
                                if(restaurantName != null)
                                    deliveryController.foodInRestaurant( restaurantName );
                                break;
//                            case 4:
//                                restaurantController.searchFood();
//                                break;
                            case 4:
                                deliveryController.trackOrder();
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

        }while( !username.equals("Q") && !password.equals("Q") );
    }

}
