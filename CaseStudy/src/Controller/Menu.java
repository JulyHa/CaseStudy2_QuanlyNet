package Controller;

import Data.DataString;
import Model.*;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static ManageUser userList =  new ManageUser() ;
    static ManageComputer computerList = new ManageComputer();
    static ManageService serviceList = new ManageService();

    static ManageTurnover turnoverList = new ManageTurnover();

    public static int scannerInt(int start, int end) {
        int number;
        do {
            try {
                number = scanner.nextInt();
                if (number >= start && number <= end) return number;
                else
                {
//                    scanner.nextLine();
                    System.out.print("Enter only "+start+" or "+end+"! Retype: ");
                }
//                return -1;
            } catch (NumberFormatException | InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Enter only "+start+" or "+end+"! Retype: ");
            }
        }while(true);
    }
    public static boolean checkYesNo(){
        int check = scannerInt(0, 1);
        return check == 1;
    }

    public static void getComputerActivity(){

        ArrayList<Computer> list = computerList.listComputerChoice(true);
        if (list.size() == 0)
        {
            System.out.println("No machines online!");
            return;
        }
        int i = 0;
        System.out.println("---------------List computer-------------");
        for (Computer con : list) {
            Date date = new Date();
            Long hour = Long.valueOf(date.getHours() - list.get(i).getStartTime().getHours());
            System.out.println((i + 1) + ": " + list.get(i).getNameComputer() + " - " + hour + "h");
            i++;
        }

    }

    public static void case1_2(){
        ArrayList<Computer> list = new ArrayList<>();
        list = computerList.listComputerChoice(false);
        if (list.size() == 0) {
            System.out.println("No machines offline!");
        } else {
            int i = 0;
            System.out.println("---------------List computer-------------");
            for (Computer con : list) {
                System.out.println((i + 1) + ": " + list.get(i).getNameComputer());
                i++;
            }
            System.out.print("Enter the ID to enable (Enter 0 to exit): ");

            int index = scannerInt(0, list.size());
            if (index > 0) {
                index--;
                String name = list.get(index).getNameComputer();
                int position = computerList.findItemListComputer(name);
                Date date = new Date();
                computerList.setItemList(position, true, date);
                System.out.println("Turn on successfully!");
            }
        }
    }

    public static void case1(){
        int choice = 1;
        while (choice != 0) {
            System.out.print(DataString.menuStatus);
            choice = scannerInt(0,2);
            switch (choice) {
                case 1: {
                    getComputerActivity();
                    break;
                }
                case 2: {
                    case1_2();
                    break;
                }
                case 0: {
                    break;
                }
            }
        }

    }

    private static String Validate(String name){
        if(name.equals("")) return "Can't be left blank!";
        for (Computer com : computerList.getListComputer()) {
            if (name.equals(com.getNameComputer())) return "Name already exists! Re-enter: ";
        }
        return "";
    }

    public static void case2(){
        String name;
        Boolean status;
        int time;
//        scanner.nextLine();
        System.out.println("-------------Add machine-------------");
        while (true){
            System.out.print("Machine name: "); name = scanner.nextLine();
            String res = Validate(name);
            if(res.equals("")){
                Computer com = new Computer(name);
                computerList.addItemListComputer(com);
                break;
            }
            else{
                System.out.println(res);
            }
        }
    }

    public static void case3(){

        computerList.printListComputer();
        System.out.print("Enter the machine ID to edit the ordinal number: ");
        int id1, id2;
        id1 = scannerInt(1,computerList.size());
        System.out.print("Enter new location: ");

        while (true){
            id2 = scannerInt(1, computerList.size());
            if(id1 == id2) System.out.print("2 ordinal numbers cannot match! Retype: ");
            else {
                System.out.println("Successfully swapped!");
                break;
            }
        }
        computerList.swap(id1, id2);
    }

    public static void case4(){
        computerList.printListComputer();
        System.out.print("\nEnter the machine ID to delete: ");
        int index = scannerInt(1, computerList.size());
        System.out.print("Are you sure you want to delete it? Yes:1, No:0 : ");
        boolean check = checkYesNo();
        if(!check){
            System.out.println("Cancel delete");
        }
        else{
            computerList.deleteItemListComputer(index);
            System.out.println("Delete successfully!");
        }
    }

    public static int validateSevice(String name){
        if(name.equals(""))return -1;
        if(serviceList.findItemListService(name) >=0 ) return 0;

        return 1;
    }

    public static void case5(){
        String serviceName;
        int money;
        System.out.print("Enter the service name: ");
        scanner.nextLine();
        while (true){
            serviceName = scanner.nextLine();
            int check = validateSevice(serviceName);
            if(check == 1){
                break;
            }
            else if(check == 0){
                System.out.print("Can't be left blank! Retype: ");
            }
            else {
                System.out.print("This service already exists! Retype: ");
            }
        }

        System.out.print("Enter service price: ");
        money = scannerInt(0, 999999999);
        serviceList.addItemListService(new Service(serviceName, money));
        System.out.println("Add new successfully!");
    }
    public static void case6_1(int position){
        System.out.println("------------------Billl-------------");
        System.out.println(" * Time: " + computerList.getItemListComputer(position).getHour());
        System.out.println(" * Sever: ");
        ArrayList<Service> services = computerList.getItemListComputer(position).getServices();
        for(int i= 0; i< services.size(); i++){
            System.out.println("    "+ (i+1) + ": " + services.get(i).getNameService() + " - " + services.get(i).getPrice());
        }
        int total = computerList.getItemListComputer(position).sumMoney();
        System.out.println(" ====> The amount to be paid is: " + total);
        computerList.setItemList(position, false, new Date());
        turnoverList.addItemTurnovers(new Turnover(computerList.getItemListComputer(position).getStartTime(), total));
    }

    public static void case6_2(int position){
        boolean out = false;

        while (true){
            System.out.println("---------------List service-------------");
            serviceList.printListService();
            System.out.print("Enter STT Service! (Enter 0 to exit): ");
            int index = scannerInt(0, serviceList.size());
            if(index == 0){
                break;
            }

            else{
                Computer com = computerList.getItemListComputer(position);
                com.addService(serviceList.getItemListServece(index));
                computerList.editItemListComputer(position, com);
                System.out.println("Add new service successful!");
                System.out.print("Would you like to continue adding services! 1.Yes   0.No: ");
                boolean check = checkYesNo();
                if(!check){
                    break;
                }

            }
        }

    }

    public static void case6(){

        int choice = 1;
        while (true) {
            System.out.print(DataString.menuPay);
            choice = scannerInt(0,2);
            if(choice == 0){
                return;
            }

            ArrayList<Computer> list = computerList.listComputerChoice(true);
            getComputerActivity();

            if (list.size() > 0) {
                int index = scannerInt(0, list.size());
                if (index != 0) {
                        index--;
                        String name = list.get(index).getNameComputer();
                        int position = computerList.findItemListComputer(name);

                        switch (choice) {
                            case 1: {
                                case6_1(position);
                                break;
                            }
                            case 2: {
                                case6_2(position);
                                break;
                            }
                        }
                    }
            }

        }

    }

    private static void case7_1(){
        //Thêm mới
        String fullName, password;
        System.out.print("Account: ");
        scanner.nextLine();
        while (true){
            fullName = scanner.nextLine();
            if(fullName.equals("")) System.out.print("Can't be left blank! Retype: ");
            else {
                boolean check = userList.findItemListUser(fullName);
                if(check){
                    System.out.print("Account already exists! Retype: ");
                }
                else break;
            }
        }
        System.out.print("Password: ");
        password = scanner.nextLine();
        userList.addItemListUser(new User(fullName, password));
        System.out.println("Add successful!");
    }

    private static void case7_2() {
        //Sửa
        case7_4();
        System.out.print("Enter the machine ID to be repaired: ");
        int index = scannerInt(1, userList.size());
        System.out.println("Account: "+ userList.getItemListUser(index).getUsername()+
            "\nPassword: " + userList.getItemListUser(index).getPassword());
        User u = new User();
        scanner.nextLine();
        while (true){
            System.out.print("Enter new account: ");u.setUsername(scanner.nextLine());
            System.out.print("Enter new password: "); u.setPassword(scanner.nextLine());
            if(userList.editItemListUser(index, u)){
                System.out.println("Edit successful!");
                break;
            }
            else{
                System.out.println("New account and new password must not be the same as the old account and old password!");
            }
        }


    }

    private static void case7_3(){
        //Xóa
        case7_4();
        System.out.print("Select the account ID to delete: ");
        int index = scannerInt(1, userList.size());
        System.out.print("Are you sure you want to delete this account? 1.Yes  0.No : ");
        boolean check = checkYesNo();
        if (!check) {
            System.out.println("Cancel delete!");
        } else{
            userList.deleteItemListUser(index);
            System.out.println("Delete successfully! ");
        }

    }

    private static void case7_4(){
        //Danh sách
        System.out.println("-----------------------List account-----------------------");
        userList.printListUser();
    }

    public static void case7(){
        int index;
        while (true){
            System.out.print(DataString.menuAccount);
            index = scannerInt(0,4);
            switch (index){
                case 0:{
                    return;
                }
                case 1:{
                    case7_1();
                    case7_4();
                    System.out.print("Enter any key to continue: ");
                    scanner.nextLine();
                    break;
                }
                case 2:{
                    case7_2();
                    case7_4();
                    System.out.print("Enter any key to continue: ");
                    scanner.nextLine();
                    break;
                }
                case 3:{
                    case7_3();
                    break;
                }
                case 4:{
                    case7_4();
                    System.out.print("Enter any key to continue: ");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                }
            }
        }

    }
    private static void case8_2(){
        String start, end;
        scanner.nextLine();
        while (true) {
            System.out.println("Enter start date(dd/MM/yyyy): ");
            start = scanner.nextLine();
            System.out.println("Enter end date(dd/MM/yyyy): ");
            end = scanner.nextLine();
            try {
                int total = 0;
                ArrayList<Turnover> turnovers = turnoverList.getTotalDate(new Date(start), new Date(end));
                for (Turnover t:turnovers ) {
                    total += t.getMoney();
                }
                System.out.println("Total from " + start + " -> " + end + ": " + total);
                break;
            } catch (Exception e) {
                System.out.println("Date input error!");
            }
        }

    }
    private static void case8_3() {
        System.out.println("--------------------Monthly revenue--------------------");
        System.out.printf("%20s %20s\n", "Month", "Money");
        for (int i=1; i<=12; i++){
            int total = 0;
            for (Turnover t : turnoverList.getTurnovers()) {
                if(t.getDate().getMonth() == (i-1)){
                    total += t.getMoney();
                }

            }
            System.out.printf("%20s %20s\n", DataString.MONTH[i-1],total );
//            System.out.println( + " revenue: " + );
        }
    }
    public static void case8(){
        System.out.println(DataString.menuTotalRevenue);
        int choice = scannerInt(0,3);
        switch (choice){
            case 0:
                break;
            case 1:
                System.out.println("Total turnover: " + turnoverList.getTotalTurnover() );
                break;
            case 2:
                case8_2();
                break;
            case 3:
                case8_3();
                break;
        }

    }

}
