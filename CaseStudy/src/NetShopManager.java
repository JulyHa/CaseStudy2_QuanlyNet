import Controller.Menu;
import Model.ManageUser;
import Model.User;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import Data.DataString;


public class NetShopManager {
    static ManageUser userList = new ManageUser();
    static Scanner scanner = new Scanner(System.in);

    public static boolean Login(User user){
        return userList.findItemListUser(user);
    }

    public static void run(){
        System.out.print(DataString.menu);
        while (true) {
            int choice =  0;
            try{
                choice = scanner.nextInt();

                switch (choice) {

                    case 1: {
                        Menu.case1();
                        break;
                    }
                    case 2:{
                        Menu.case2();
                        break;
                    }
                    case 3:{
                        Menu.case3();
                        break;
                    }
                    case 4:{
                        Menu.case4();

                        break;
                    }
                    case 5:{
                        Menu.case5();
                        break;
                    }
                    case 6:{
                        System.out.println("Nooooooooooooo :<");
                        break;
                    }
                    case 7:{
                        Menu.case7();
                        break;
                    }
                    case 8:{
                        Menu.case8();
                        break;
                    }
                    case 9:{
                        System.out.println("Noooooooooooooo :<");
                        break;
                    }

                    case 10:{
                        System.exit(0);
                    }

                    default: {
                        System.out.println("No command above! Retype: ");
                        break;
                    }

                }
                System.out.print(DataString.menu);
            } catch (NumberFormatException | InputMismatchException e){
                System.out.print("Enter only numbers in the range (1-10)! Retype: ");
                scanner.nextLine();

            }

        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        User user = new User();
        while (true){
            boolean check = false;
            System.out.print("Account: ");
            user.setUsername(scanner.nextLine() );
            System.out.print("Password: ");
            user.setPassword(scanner.nextLine() );
            if (Login(user)) break;
            else System.out.println("Incorrect account or password ");
        }

        run();


    }


}
