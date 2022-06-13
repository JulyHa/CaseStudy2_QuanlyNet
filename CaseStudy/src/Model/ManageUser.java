package Model;

import Controller.ReadWriteFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManageUser {
    ArrayList<User> listUser = new ArrayList<>();
    final ReadWriteFile readWriteFile = new ReadWriteFile();
    String path = "D:\\Uyen\\TaiLieuDiLam\\CodeGym\\CodeJava\\CaseStudy\\src\\Data\\Account.txt";

    @Override
    public String toString() {
        String s ="";
        for (User u : listUser){
            s+= u.toString()+"\n";
        }
        return s;
    }

    private void readFile(){
        try {
            ArrayList<String>list = readWriteFile.readFile((path));
            for (String line : list){
                String[] s = line.split("-");
                User u = new User(s[0],s[1]);
                listUser.add(u);
            }
        } catch ( IOException e) {
            listUser = new ArrayList<>();
        }
    }

    public ManageUser() {
        readFile();
    }

    public User getItemListUser(int position) {
        position--;
        if(position > listUser.toArray().length || position < 0)
            return null;
        return listUser.get(position);
    }

    public boolean setItemListUser(int position, User user) {
        if(position > listUser.toArray().length || position < 0)
            return false;
        this.listUser.get(position).setUsername(user.getUsername());
        this.listUser.get(position).setPassword(user.getPassword());
        return true;
    }

    public void addItemListUser(User user) {
        listUser.add(user);
        String s = "";
        for (User u : listUser){
            s+= u +"\n";
        }
        readWriteFile.wirteFile(path, s);
    }

    private Boolean Validate(int position, User user) {
        if (user.getUsername().equals("") || user.getPassword().equals(""))
            return false;
        if (user.getPassword().equals(listUser.get(position).getPassword()) ||
                user.getUsername().equals(listUser.get(position).getUsername()))
            return false;
        return true;
    }

    public Boolean editItemListUser(int position, User user) {
        position--;
        if(position > listUser.toArray().length || position < 0)
            return false;
        if (Validate(position, user)) {
            this.listUser.get(position).setUsername(user.getUsername());
            this.listUser.get(position).setPassword(user.getPassword());

            String s = "";
            for (User u : listUser){
                s+= u +"\n";
            }
            return true;
        }
        return false;
    }

    public void deleteItemListUser(int position) {
        listUser.remove(position-1);
        String s = "";
        for (User u : listUser){
            s+= u +"\n";
        }
        readWriteFile.wirteFile(path, s);
    }

    public boolean findItemListUser(User user) {
        for (User u : listUser) {
            if (u.getUsername().equals(user.getUsername()) &&
                    u.getPassword().equals(user.getPassword())) return true;
        }
        return false;
    }
    public boolean findItemListUser(String fullname) {
        for (User u : listUser) {
            if (u.getUsername().equals(fullname)) return true;
        }
        return false;
    }

    public int size() {
        return listUser.size();
    }

    public void printListUser() {
        if(listUser.toArray().length == 0){
            System.out.printf("List is empty!");
            return;
        }
        int count = 0;
        for (User user : listUser) {
            count++;
            System.out.println(count +": " + user);
        }
    }

    public void printItemListUser(int i) {
        String s = listUser.get(i).getUsername() + " - ";
    }

}
