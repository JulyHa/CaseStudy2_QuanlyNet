package Controller;

import Model.*;

import java.util.*;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static Boolean end_CaseStudy = false;
    static ManageUser userList =  new ManageUser() ;
    static ManageComputer computerList = new ManageComputer();
    static ManageService serviceList = new ManageService();
    static String menu = """
            -------------------------------PHẦN MỀM TÍNH TIỀN--------------------
            |           1. Hiển thị danh sách máy có trong quán                 |
            |           2.      Thêm 1 máy mới vào danh sách                    |
            |           3.      Sửa đổi thông tin của máy                       |
            |           4.      Xóa 1 máy khỏi danh sách                        |
            |           5.           Thêm dịch vụ                               |
            |           6.   Chỉnh sửa tính tiền theo giờ                       |
            |           7.          Tính tiền                                   |
            |           8.      Quản lý tài khoản đăng nhập                     |
            |           9.          Doanh thu                                   |
            |           10.  >>>>>>>>>>>> Thoát <<<<<<<<<<<<<                   |
            -------------------------------Chọn chức năng------------------------
            Chọn chức năng:  """;
    static String menuAccount = """
            ---------------------------Quản lý tài khoản------------------------
            |                       1. Thêm mới                                 |
            |                       2. Sửa                                      |
            |                       3. Xóa                                      |
            |                       4. Danh sách các tài khoản                  |
            |                       0. Thoát                                    |
            ----------------------------Chọn chức năng--------------------------
            Chọn chức năng:             """;

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
        System.out.println("1. Hiển thị các máy đang online" +
                "\n2. Hiển thị các máy đang offline" +
                "\n0. Thoát" +
                "\n-------------Chọn chức năng----------");
        int choice;
        while (true) {
            choice = scannerInt();
            if(choice < 0 || choice > 2) System.out.printf("Chỉ nhập từ (0-2)! Nhập lại: ");
            else{
                break;
            }
        }
        ArrayList<Computer> list = new ArrayList<>();
        switch (choice){
            case 1:{
                list = computerList.listComputerChoice(true);
                if(list.size() == 0) System.out.println("Không có máy nào online!");
                else{
                    int i = 0;
                    for(Computer con : list){
                        int hour = Calendar.getInstance().getTime().getHours()-list.get(i).getStartTime().getHours();
                        System.out.println((i+1)+ ": "+ list.get(i).getNameComputer() + " - "+ hour +"h");
                        i++;
                    }
                }
                break;
            }
            case 2:{

                list = computerList.listComputerChoice(false);
                if(list.size() == 0){
                    System.out.println("Không có máy nào offline!");
                }
                else{
                    int i = 0;
                    for(Computer con : list){
                        System.out.println((i+1)+ ": "+ list.get(i).getNameComputer());
                        i++;
                    }
                    System.out.printf("Nhập ID cần bật (Nhập 0 để thoát): ");

                    int index;
                    while (true) {
                        index = scannerInt();
                        if(index == -1 ||  index > list.size()) System.out.printf("Yêu cầu nhập số trong khoảng (0-"+list.size()+"): ");
                        else if(index == 0) {
                            break;
                        }
                        else {
                            index--;
                            String name = list.get(index).getNameComputer();
                            int position = computerList.findItemListComputer(name);
                            computerList.setItemList(position, true);
                            System.out.println("Bật thành công!");

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
        if(name.equals("")) return "Không được để trống!";
        for (Computer com : computerList.getListComputer()) {
            if (name.equals(com.getNameComputer())) return "Đã tồn tại tên!Nhập lại!";
        }
        return "";
    }

    public void case2(){
        String name;
        Boolean status;
        int time;
        scanner.nextLine();
        System.out.println("-------------Thêm máy-------------");
        while (true){
            System.out.printf("Tên máy: "); name = scanner.nextLine();
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
        System.out.printf("Nhập ID máy cần sửa số thứ tự: ");
        int id1, id2;
        while (true){
            id1 = scannerInt();
            if(id1 <= 0 || id1 > computerList.size()) System.out.printf("Yêu cầu nhập số trong khoảng(1-" + computerList.size()+"): ");
            else break;
        }
        System.out.printf("Nhập số thứ tự mới: ");
        while (true){
            id2 = scannerInt();
            if(id1 == id2) System.out.printf("2 số thứ tự không được trùng! Nhập lại: ");
            else if(id2 <= 0 || id2 > computerList.size()) System.out.printf("Yêu cầu nhập số trong khoảng(1-" + computerList.size()+"): ");
            else {
                System.out.println("Đổi chỗ thành công!");
                break;
            }
        }
        computerList.swap(id1, id2);
    }

    public void case4(){
        computerList.printListComputer();
        System.out.printf("\nNhập ID máy để xóa");
        int index;
        while (true) {
            index = scannerInt();
            if(index <= 0 || index > computerList.size()) System.out.printf("Yêu cầu nhập số trong khoảng(1-" + computerList.size()+"): ");
            else break;
        }
        System.out.printf("Bạn có chắc muốn xóa không? yes nhập 1, no nhập 0: ");
        int check;
        while (true){
            check = scannerInt();
            if(check == 0){
                System.out.println("Hủy xóa");
                break;
            }
            else if(check == 1){
                computerList.deleteItemListComputer(index);
                System.out.println("Xóa thành công!");
                break;
            }
            else System.out.printf("Chỉ nhập 0 hoặc 1! Nhập lại: ");
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
        System.out.printf("Nhập tên dịch vụ: ");
        scanner.nextLine();
        while (true){
            serviceName = scanner.nextLine();
            int check = validateSevice(serviceName);
            if(check == 1){
                break;
            }
            else if(check == 0){
                System.out.printf("Không được để trống! Nhập lại: ");
            }
            else {
                System.out.printf("Đã tồn tại dịch vụ này! Nhập lại: ");
            }
        }

        System.out.printf("Nhập giá dịch vụ: ");
        while (true){
            money = scannerInt();
            if(money <= 0) System.out.printf("Giá là số nguyên dương > 0! Nhập lại: ");
            else break;
        }
        serviceList.addItemListService(new Service(serviceName, money));
        System.out.println("Thêm thành công!");
    }

    public void case7_1(){
        ArrayList<Computer> list = computerList.listComputerChoice(true);
        if(list.size() == 0) System.out.println("Không có máy nào online!");
        else{
            int i = 0;
            for(Computer con : list){
                int hour = Calendar.getInstance().getTime().getHours()-list.get(i).getStartTime().getHours();
                System.out.println((i+1)+ ": "+ list.get(i).getNameComputer() + " - "+ hour +"h");
                i++;
            }
            System.out.printf("Nhập ID máy cần tính tiền!(Nhập 0 để thoát): ");

            int index;
            while (true) {
                index = scannerInt();
                if(index == -1 ||  index > list.size()) System.out.printf("Yêu cầu nhập số trong khoảng (0-"+list.size()+"): ");
                else if(index == 0) {
                    break;
                }
                else {
                    index--;
                    String name = list.get(index).getNameComputer();
                    int position = computerList.findItemListComputer(name);
                    System.out.println("Tiền cần thanh toán là: " + computerList.getItemListComputer(position).sumMoney());
                    computerList.setItemList(position, false);
                    break;
                }
            }
        }
    }

    public void case7_2(int position){
        boolean out = false;
        serviceList.printListService();
        System.out.printf("Nhập STT dịch vụ!(Nhập 0 để thoát): ");
        while (true){
            int index = scannerInt();
            if(index == 0) out = true;
            else if(index<0 || index > serviceList.size())
                System.out.printf("Chỉ nhập 1 số trong khoảng(0-"+serviceList.size()+")!Nhập lại: ");
            else{
                Computer com = computerList.getItemListComputer(position);
                int money = com.getMoneyService() + serviceList.getItemListServece(index).getPrice();
                com.setMoneyService(money);
                computerList.editItemListComputer(position, com);
                System.out.println("Thêm dịch vụ thành công!");
                System.out.printf("Bạn có muốn tiếp tục thêm dịch vụ không!1. Yes 0.No: ");
                int check;
                while (true) {
                    check = scannerInt();
                    if(check < 0 || check > 1) System.out.printf("Chỉ nhập 0 hoặc 1! Nhập lại: ");
                    else if (check == 0) {
                        out = true;
                        break;
                    }
                    else{
                        serviceList.printListService();
                        System.out.printf("Nhập STT dịch vụ!(Nhập 0 để thoát): ");
                        break;
                    }
                }
                if (out) break;

            }
        }

    }

    public void case7(){
        System.out.println("--------------------Chọn chức năng----------------");
        System.out.println("1. Tính tiền");
        System.out.println("2. Thêm dịch vụ");
        System.out.println("0. Thoát");
        int choice;
        while (true){
            choice = scannerInt();
            if(choice < 0 || choice > 2) System.out.printf("Chỉ nhập số trong khoảng(0-2)!Nhập lại: ");
            else break;
        }
        ArrayList<Computer> list = computerList.listComputerChoice(true);
        if(list.size() == 0) System.out.println("Không có máy nào online!");
        else {
            int i = 0;
            for (Computer con : list) {
                int hour = Calendar.getInstance().getTime().getHours() - list.get(i).getStartTime().getHours();
                System.out.println((i + 1) + ": " + list.get(i).getNameComputer() + " - " + hour + "h");
                i++;
            }
            System.out.printf("Nhập ID máy!(Nhập 0 để thoát): ");

            int index;
            while (true) {
                index = scannerInt();
                if (index == -1 || index > list.size())
                    System.out.printf("Yêu cầu nhập số trong khoảng (0-" + list.size() + "): ");
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
                            System.out.println("Tiền cần thanh toán là: " + computerList.getItemListComputer(position).sumMoney());
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
        System.out.printf("Nhập user name: ");
        scanner.nextLine();
        while (true){
            fullName = scanner.nextLine();
            if(fullName.equals("")) System.out.printf("Không được để trống! Nhập lại: ");
            else {
                boolean check = userList.findItemListUser(fullName);
                if(check == true){
                    System.out.printf("Tài khoản đã tồn tại! Nhập lại: ");
                }
                else break;
            }
        }
        System.out.printf("Nhập mật khẩu: ");
        password = scanner.nextLine();
        userList.addItemListUser(new User(fullName, password));
        System.out.println("Thêm mới thành công!");
    }

    private void case8_2() {
        //Sửa
        case8_4();
        System.out.printf("Nhập ID máy cần sửa: ");
        int index;
        while (true) {
            index = scannerInt();
            if (index <= 0 || index > userList.size())
                System.out.printf("Chỉ nhập số trong khoảng (1-" + userList.size() + "). Nhập lại: ");
            else break;
        }
        System.out.println("User name: "+ userList.getItemListUser(index).getUsername()+
            "\nPassword: " + userList.getItemListUser(index).getPassword());
        User u = new User();
        scanner.nextLine();
        while (true){
            System.out.printf("Nhập user name mới: ");u.setUsername(scanner.nextLine());
            System.out.printf("Nhập password mới: "); u.setPassword(scanner.nextLine());
            if(userList.editItemListUser(index, u)){
                System.out.printf("Sửa thành công!");
                break;
            }
            else{
                System.out.println("User name mới và password mới không được trùng với user name cũ và password cũ!");
            }
        }


    }

    private void case8_3(){
        //Xóa
        case8_4();
        System.out.printf("Chọn ID tài khoản cần xóa: ");
        int index;
        while (true) {
            index = scannerInt();
            if (index <= 0 || index > userList.size())
                System.out.printf("Chỉ nhập số trong khoảng (1-" + userList.size() + "). Nhập lại: ");
            else break;
        }
        System.out.printf("Bạn có chắc chắn muốn xóa tài khoản này? 1.Yes  0. No : ");
        int check;
        while (true) {
            check = scannerInt();
            if (check == 0) {
                System.out.println("Hủy xóa");
                break;
            } else if (check == 1) {
                userList.deleteItemListUser(index);
                System.out.println("Xóa thành công");
                break;
            } else System.out.printf("Chỉ nhập 0 hoặc 1! Nhập lại: ");
        }
    }

    private void case8_4(){
        //Danh sách
        System.out.println("-----------------------Danh sách tài khoản-----------------------");
        userList.printListUser();
    }

    public void case8(){
        System.out.printf(menuAccount);
        int index;
        while (true){
            index = scannerInt();
            if(index < 0 || index > 4) System.out.printf("Yêu cầu nhập số trong khoảng(0-4): ");
            else {
                switch (index){
                    case 0:{
                        return;
                    }
                    case 1:{
                        case8_1();
                        case8_4();
                        System.out.printf("Nhập 1 phím bất kỳ để tiếp tục: ");
                        scanner.nextLine();
                        break;
                    }
                    case 2:{
                        case8_2();
                        case8_4();
                        System.out.printf("Nhập 1 phím bất kỳ để tiếp tục: ");
                        scanner.nextLine();
                        break;
                    }
                    case 3:{
                        case8_3();
                        break;
                    }
                    case 4:{
                        case8_4();
                        System.out.printf("Nhập 1 phím bất kỳ để tiếp tục: ");
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
                        System.out.println("Chưa làm");
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
                        System.out.println("Chưa làm");
                        break;
                    }

                    case 10:{
                        System.exit(0);
                    }

                    default: {
                        System.out.println("Không có lệnh trên! Nhập lại: ");
                        break;
                    }

                }
                System.out.print(menu);
            } catch (NumberFormatException | InputMismatchException e){
                System.out.print("Chỉ nhập số trong khoảng (1-10)! Nhập lại: ");
                scanner.nextLine();

            }

        }

    }

//    public static void main(String[] args) {
//        Menu menu = new Menu();
//        menu.run();
//    }
}
