package CaseStudy;

import CaseStudy.Controller.Menu;

public class EmployeeManagementProgram {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.printMenu();
        menu.selectMenu();
    }
}
