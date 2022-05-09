package CaseStudy.Model;

import CaseStudy.Controller.Read_Write;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManageStaff {
    List<Staff> staffList;
    Read_Write read_write = new Read_Write();

    public ManageStaff() {
        try {
            staffList = read_write.readFile("D:\\Work\\CodeGym\\Code\\Data\\Employee.txt");
        }catch (IOException e){
            staffList = new ArrayList<>();
        }

    }

    public void printStaff(){
        if(staffList.size() == 0){
            System.out.println("Không có nhân viên");
            return;
        }
        for(Staff staff : staffList){
            System.out.println(staff);
        }
    }

    public void addStaff(Staff staff){
        staffList.add(staff);
        try{
            if(read_write.wirteFile("D:\\Work\\CodeGym\\Code\\Data\\Employee.txt", staffList)){
                System.out.println("Thêm thành công!");
            }else {
                System.out.println("Thêm không thành công");
            }
        }catch (IOException e){
            System.out.println("Không doc duoc file");
        }

    }
    public void editStaff(String id, Staff staff) throws IOException {
        int pos = 0;
        int len = staffList.size();
        for(int i = 0; i < len; i++){
            Staff st = staffList.get(i);
            if(Objects.equals(st.getId(), id)){
                pos = staffList.indexOf(st);
                break;
            }
        }
        if(pos > 0){
            staffList.get(pos).setFullName(staff.getFullName());
            staffList.get(pos).setHomeAddress(staff.getHomeAddress());
            staffList.get(pos).setPhoneNumber(staff.getPhoneNumber());
            staffList.get(pos).setWork(staff.isWork());
            staffList.get(pos).setYearOfBirth(staff.getYearOfBirth());
            if(read_write.wirteFile("Employee.txt", staffList)){
                System.out.println("Sửa thành công!");
            }else {
                System.out.println("Sửa không thành công");
            }
        }
        else{
            System.out.println("Không có nhân viên này!");
        }
    }
    public void deleteStaff(String id) throws IOException {
        int pos = 0;
        int len = staffList.size();
        for(int i = 0; i < len; i++){
            Staff st = staffList.get(i);
            if(Objects.equals(st.getId(), id)){
                pos = staffList.indexOf(st);
                break;
            }
        }
        if(pos > 0){
            staffList.remove(pos);
            if(read_write.wirteFile("Employee.txt", staffList)){
                System.out.println("Xóa thành công!");
            }else {
                System.out.println("Xóa không thành công");
            }
        }
        else{
            System.out.println("Không có nhân viên này!");
        }
    }
}
