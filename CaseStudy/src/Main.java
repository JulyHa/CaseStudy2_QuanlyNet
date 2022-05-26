import Controller.Menu;
import Model.Computer;
import Model.ManageComputer;
import Model.ManageUser;
import Model.User;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ManageUser userList = new ManageUser();
    static Scanner scanner = new Scanner(System.in);

    public static boolean Login(User user){
        return userList.findItemListUser(user);
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

        Menu menu = new Menu();
        menu.run();


    }


}
