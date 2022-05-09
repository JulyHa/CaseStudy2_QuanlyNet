package CaseStudy.Controller;

import CaseStudy.Model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Read_Write {
    public List<Staff> readFile(String path) throws IOException {
        List<Staff> staffList = new ArrayList<>();
        FileInputStream fi = null;
        ObjectInputStream inputStream = null;
        try {
            fi = new FileInputStream(path);
            inputStream = new ObjectInputStream(fi);
            staffList = (List<Staff>) inputStream.readObject();
            inputStream.close();
            fi.close();
            return staffList;
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }
    public boolean wirteFile(String path, List<Staff> staffs) throws IOException {
        ObjectOutputStream outputStream = null;
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(path);
            outputStream = new ObjectOutputStream(fo);
            outputStream.writeObject(staffs);
        } finally {
            outputStream.close();
            fo.close();
        }
        return true;
    }
}
