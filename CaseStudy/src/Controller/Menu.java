package Controller;

import Model.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static Boolean end_CaseStudy = false;
    static ManageUser userList =  new ManageUser() ;
    static ManageComputer computerList = new ManageComputer();
    static ManageService serviceList = new ManageService();
    static String menu =
            "---------------------MONEY CALCULATION SOFTWARE---------------------\n"+
            "|           1. Display the list of machines available in the shop   |\n"+
            "|           2.      Add a new device to the list                    |\n"+
            "|           3.      Modify machine information                      |\n"+
            "|           4.      Remove a machine from the list                  |\n"+
            "|           5.           More services                              |\n"+
            "|           6.         Edit hourly billing                          |\n"+
            "|           7.                 Bill                                 |\n"+
            "|           8.              Manage account                          |\n"+
            "|           9.                 Turnover                             |\n"+
            "|           10.  >>>>>>>>>>>> Exit <<<<<<<<<<<<<                    |\n"+
            "--------------------------------------------------------------------\n"+
            "Choice:  ";
    static String menuAccount =
            "--------------------Manage account -----------------\n"+
            "|             1. Add new a account                  |\n"+
            "|             2. Edit account                       |\n"+
            "|             3. Delete account                     |\n"+
            "|             4. List of accounts                   |\n"+
            "|             0. >>>>>>>>>>>> Exit <<<<<<<<<<<<     |\n"+
            "----------------------------------------------------\n"+
            "Choice:             ";

    public int scannerInt() {
        int n;
        try {
            n = scanner.nextInt();
            if (n >= 0) return n;
            return -1;
        } catch (NumberFormatException | InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    public void case1(){
        System.out.println("1. Show devices that are online" +
                "\n2. Show devices that are offline" +
                "\n0. Exit" +
                "\n------------------------------" +
                "\nChoice: ");
        int choice;
        while (true) {
            choice = scannerInt();
            if(choice < 0 || choice > 2) System.out.printf("Enter only words (0-2)! Retype: ");
            else{
                break;
            }
        }
        ArrayList<Computer> list = new ArrayList<>();
        switch (choice){
            case 1:{
                list = computerList.listComputerChoice(true);
                if(list.size() == 0) System.out.println("No machines online!");
                else{
                    int i = 0;
                    for(Computer con : list){
                        Date date = new Date();
                        Long hour = Long.valueOf(date.getHours() -list.get(i).getStartTime().getHours());
                        System.out.println((i+1)+ ": "+ list.get(i).getNameComputer() + " - "+ hour +"h");
                        i++;
                    }
                }
                break;
            }
            case 2:{

                list = computerList.listComputerChoice(false);
                if(list.size() == 0){
                    System.out.println("No machines offline!");
                }
                else{
                    int i = 0;
                    for(Computer con : list){
                        System.out.println((i+1)+ ": "+ list.get(i).getNameComputer());
                        i++;
                    }
                    System.out.printf("Enter the ID to enable (Enter 0 to exit): ");

                    int index;
                    while (true) {
                        index = scannerInt();
                        if(index == -1 ||  index > list.size()) System.out.printf("Enter only words (0-"+list.size()+")!  Retype: ");
                        else if(index == 0) {
                            break;
                        }
                        else {
                            index--;
                            String name = list.get(index).getNameComputer();
                            int position = computerList.findItemListComputer(name);
                            computerList.setItemList(position, true);
                            System.out.println("Turn on successfully!");

                            break;
                        }
                    }
                }
                break;
            }
            case 0:{
                break;
            }
        }

    }

    private String Validate(String name){
        if(name.equals("")) return "Can't be left blank!";
        for (Computer com : computerList.getListComputer()) {
            if (name.equals(com.getNameComputer())) return "Name already exists! Re-enter: ";
        }
        return "";
    }

    public void case2(){
        String name;
        Boolean status;
        int time;
        scanner.nextLine();
        System.out.println("-------------Add machine-------------");
        while (true){
            System.out.printf("Machine name: "); name = scanner.nextLine();
            String res = Validate(name);
            if(res == ""){
                Computer com = new Computer(name);
                computerList.addItemListComputer(com);
                break;
            }
            else{
                System.out.println(res);
            }
        }
    }

    public void case3(){

        computerList.printListComputer();
        System.out.printf("Enter the machine ID to edit the ordinal number: ");
        int id1, id2;
        while (true){
            id1 = scannerInt();
            if(id1 <= 0 || id1 > computerList.size()) System.out.printf("Enter only words (1-" + computerList.size()+")!  Retype: ");
            else break;
        }
        System.out.printf("Nhập số thứ tự mới: ");
        while (true){
            id2 = scannerInt();
            if(id1 == id2) System.out.printf("2 ordinal numbers cannot match! Retype: ");
            else if(id2 <= 0 || id2 > computerList.size()) System.out.printf("Enter only words (1-" + computerList.size()+")!  Retype: ");
            else {
                System.out.println("Successfully swapped!");
                break;
            }
        }
        computerList.swap(id1, id2);
    }

    public void case4(){
        computerList.printListComputer();
        System.out.printf("\nEnter the machine ID to delete: ");
        int index;
        while (true) {
            index = scannerInt();
            if(index <= 0 || index > computerList.size()) System.out.printf("Enter only words (1-" + computerList.size()+")! Retype: ");
            else break;
        }
        System.out.printf("Are you sure you want to delete it? Yes:1, No:0 : ");
        int check;
        while (true){
            check = scannerInt();
            if(check == 0){
                System.out.println("Cancel delete");
                break;
            }
            else if(check == 1){
                computerList.deleteItemListComputer(index);
                System.out.println("Delete successfully!");
                break;
            }
            else System.out.printf("Enter only 0 or 1! Retype: ");
        }

    }

    public int validateSevice(String name){
        if(name.equals(""))return -1;
        if(serviceList.findItemListService(name) >=0 ) return 0;

        return 1;
    }

    public void case5(){
        String serviceName;
        int money;
        System.out.printf("Enter the service name: ");
        scanner.nextLine();
        while (true){
            serviceName = scanner.nextLine();
            int check = validateSevice(serviceName);
            if(check == 1){
                break;
            }
            else if(check == 0){
                System.out.printf("Can't be left blank! Retype: ");
            }
            else {
                System.out.printf("This service already exists! Retype: ");
            }
        }

        System.out.printf("Enter service price: ");
        while (true){
            money = scannerInt();
            if(money <= 0) System.out.printf("Price is a positive integer > 0! Retype: ");
            else break;
        }
        serviceList.addItemListService(new Service(serviceName, money));
        System.out.println("Add new successfully!");
    }

//    public void case7_1(){
//        ArrayList<Computer> list = computerList.listComputerChoice(true);
//        if(list.size() == 0) System.out.println("No machines online!");
//        else{
//            int i = 0;
//            for(Computer con : list){
//                int hour = Calendar.getInstance().getTime().getHours()-list.get(i).getStartTime().getHours();
//                System.out.println((i+1)+ ": "+ list.get(i).getNameComputer() + " - "+ hour +"h");
//                i++;
//            }
//            System.out.printf("Enter the ID of the machine to be charged! (Enter 0 to exit): ");
//
//            int index;
//            while (true) {
//                index = scannerInt();
//                if(index == -1 ||  index > list.size()) System.out.printf("Enter only words (0-"+list.size()+"): ");
//                else if(index == 0) {
//                    break;
//                }
//                else {
//                    index--;
//                    String name = list.get(index).getNameComputer();
//                    int position = computerList.findItemListComputer(name);
//                    System.out.println("The amount to be paid is: " + computerList.getItemListComputer(position).sumMoney());
//                    computerList.setItemList(position, false);
//                    break;
//                }
//            }
//        }
//    }

    public void case7_2(int position){
        boolean out = false;
        serviceList.printListService();
        System.out.printf("Enter STT Service! (Enter 0 to exit): ");
        while (true){
            int index = scannerInt();
            if(index == 0) out = true;
            else if(index<0 || index > serviceList.size())
                System.out.printf("Enter only words (0-"+serviceList.size()+")! Retype: ");
            else{
                Computer com = computerList.getItemListComputer(position);
                int money = com.getMoneyService() + serviceList.getItemListServece(index).getPrice();
                com.setMoneyService(money);
                computerList.editItemListComputer(position, com);
                System.out.println("Add new service successful!");
                System.out.printf("Would you like to continue adding services! 1.Yes   0.No: ");
                int check;
                while (true) {
                    check = scannerInt();
                    if(check < 0 || check > 1) System.out.printf("Enter only 0 or 1! Retype: ");
                    else if (check == 0) {
                        out = true;
                        break;
                    }
                    else{
                        serviceList.printListService();
                        System.out.printf("Enter STT Service! (Enter 0 to exit): ");
                        break;
                    }
                }
                if (out) break;

            }
        }

    }

    public void case7(){
        System.out.println("--------------------Choice----------------");
        System.out.println("1. Bill");
        System.out.println("2. Add service");
        System.out.println("0. Exit");
        int choice;
        while (true){
            choice = scannerInt();
            if(choice < 0 || choice > 2) System.out.printf("Enter only numbers in the range(0-2)!Re-enter: ");
            else break;
        }
        ArrayList<Computer> list = computerList.listComputerChoice(true);
        if(list.size() == 0) System.out.println("No machines online!");
        else {
            int i = 0;
            for (Computer con : list) {
                Date date = new Date();
                int hour = date.getHours() - list.get(i).getStartTime().getHours();
                System.out.println((i + 1) + ": " + list.get(i).getNameComputer() + " - " + hour + "h");
                i++;
            }
            System.out.printf("Enter the machine ID! (Enter 0 to exit): ");

            int index;
            while (true) {
                index = scannerInt();
                if (index == -1 || index > list.size())
                    System.out.printf("Enter only numbers in the range (0-" + list.size() + "): ");
                else if (index == 0) {
                    break;
                } else {
                    index--;
                    String name = list.get(index).getNameComputer();
                    int position = computerList.findItemListComputer(name);

                    switch (choice){
                        case 0:{
                            break;
                        }
                        case 1:{
                            System.out.println("The amount to be paid is: " + computerList.getItemListComputer(position).sumMoney());
                            computerList.setItemList(position, false);
                            break;
                        }
                        case 2:{
                            case7_2(position);
                            break;
                        }
                    }
                    break;
                }
            }
        }

    }

    private void case8_1(){
        //Thêm mới
        String fullName, password;
        System.out.printf("Account: ");
        scanner.nextLine();
        while (true){
            fullName = scanner.nextLine();
            if(fullName.equals("")) System.out.printf("Can't be left blank! Retype: ");
            else {
                boolean check = userList.findItemListUser(fullName);
                if(check == true){
                    System.out.printf("Account already exists! Retype: ");
                }
                else break;
            }
        }
        System.out.printf("Password: ");
        password = scanner.nextLine();
        userList.addItemListUser(new User(fullName, password));
        System.out.println("Add successful!");
    }

    private void case8_2() {
        //Sửa
        case8_4();
        System.out.printf("Enter the machine ID to be repaired: ");
        int index;
        while (true) {
            index = scannerInt();
            if (index <= 0 || index > userList.size())
                System.out.printf("Enter only numbers in the range (1-" + userList.size() + "): ");
            else break;
        }
        System.out.println("Account: "+ userList.getItemListUser(index).getUsername()+
            "\nPassword: " + userList.getItemListUser(index).getPassword());
        User u = new User();
        scanner.nextLine();
        while (true){
            System.out.printf("Enter new account: ");u.setUsername(scanner.nextLine());
            System.out.printf("Enter new password: "); u.setPassword(scanner.nextLine());
            if(userList.editItemListUser(index, u)){
                System.out.printf("Edit successful!");
                break;
            }
            else{
                System.out.println("new account and new password must not be the same as the old account and old password!");
            }
        }


    }

    private void case8_3(){
        //Xóa
        case8_4();
        System.out.printf("Select the account ID to delete: ");
        int index;
        while (true) {
            index = scannerInt();
            if (index <= 0 || index > userList.size())
                System.out.printf("Enter only numbers in the range (1-" + userList.size() + "): ");
            else break;
        }
        System.out.printf("Are you sure you want to delete this account? 1.Yes  0.No : ");
        int check;
        while (true) {
            check = scannerInt();
            if (check == 0) {
                System.out.println("Cancel delete!");
                break;
            } else if (check == 1) {
                userList.deleteItemListUser(index);
                System.out.println("Delete successfully! ");
                break;
            } else System.out.printf("Enter only 0 or 1! Retype: ");
        }
    }

    private void case8_4(){
        //Danh sách
        System.out.println("-----------------------List account-----------------------");
        userList.printListUser();
    }

    public void case8(){
        System.out.printf(menuAccount);
        int index;
        while (true){
            index = scannerInt();
            if(index < 0 || index > 4) System.out.printf("Required to enter a number in the range (0-4): ");
            else {
                switch (index){
                    case 0:{
                        return;
                    }
                    case 1:{
                        case8_1();
                        case8_4();
                        System.out.printf("Enter any key to continue: ");
                        scanner.nextLine();
                        break;
                    }
                    case 2:{
                        case8_2();
                        case8_4();
                        System.out.printf("Enter any key to continue: ");
                        scanner.nextLine();
                        break;
                    }
                    case 3:{
                        case8_3();
                        break;
                    }
                    case 4:{
                        case8_4();
                        System.out.printf("Enter any key to continue: ");
                        scanner.nextLine();
                        scanner.nextLine();
                        break;
                    }
                }
                System.out.printf(menuAccount);
            }
        }
    }

    public void run(){
        System.out.print(menu);
        while (true) {
            int choice =  0;
            try{
                choice = scanner.nextInt();

                switch (choice) {

                    case 1: {
                        case1();
                        break;
                    }
                    case 2:{
                        case2();
                        break;
                    }
                    case 3:{
                        case3();
                        break;
                    }
                    case 4:{
                        case4();

                        break;
                    }
                    case 5:{
                        case5();
                        break;
                    }
                    case 6:{
                        System.out.println("Nooooooooooooo :<");
                        break;
                    }
                    case 7:{
                        case7();
                        break;
                    }
                    case 8:{
                        case8();
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
                System.out.print(menu);
            } catch (NumberFormatException | InputMismatchException e){
                System.out.print("Enter only numbers in the range (1-10)! Retype: ");
                scanner.nextLine();

            }

        }

    }

//    public static void main(String[] args) {
//        Menu menu = new Menu();
//        menu.run();
//    }
}
