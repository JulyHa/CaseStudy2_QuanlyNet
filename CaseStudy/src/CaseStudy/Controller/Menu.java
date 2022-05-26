package CaseStudy.Controller;

import CaseStudy.Model.ManageStaff;
import CaseStudy.Model.Staff;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static ManageStaff manageStaff = new ManageStaff();

    public void printMenu() {
        String menu = "-----------Employee Management----------"+
                "1. Thêm nhân viên\n"+
                "2. Tìm kiếm nhân viên\n"+
                "3. Kiểm tra trạng thái nhân viên\n"+
                "4. Sửa thông tin nhân viên\n"+
                "5. Thay đổi trạng thái nhân viên\n"+
                "6. Thông tin tài khoản \n"+
                "7. Đăng xuất \n"+
                "----------------------------------------\n";
        System.out.println(menu);
    }

    public void selectMenu() {
        int n = 0;
        while (n != 7) {


            do {
                try {
                    System.out.println("Mời bạn chọn chức năng!\n");
                    n = sc.nextInt();
                } catch (NumberFormatException e) {
                    System.out.println("Nhập sai định dạng");
                    continue;
                }
                if (n < 1 || n > 8) {
                    System.out.println("Bạn chỉ được chọn chức năng từ 1 -> 7");
                }
            } while (1 > n || 7 < n);
            switch (n) {
                case 1:
                    manageStaff.addStaff(new Staff("1", "Ngo Van Khai", "HaNoi", "0394229171", "22/09/2001", true));
                    manageStaff.printStaff();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }
}
