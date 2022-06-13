package Model;

import Controller.ReadWriteFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManageComputer {
    ArrayList<Computer> listComputer = new ArrayList<>();
    final ReadWriteFile readWriteFile = new ReadWriteFile();
    final static String path = "D:\\Uyen\\TaiLieuDiLam\\CodeGym\\CodeJava\\CaseStudy\\src\\Data\\Computer.txt";

    private void readFile(){

        try {
            ArrayList<String>list = readWriteFile.readFile((path));
            for (String line : list){
                String[] s = line.split("-");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = (Date)sdf.parse(s[2]);
                ArrayList<Service> services = new ArrayList<>();
                if(s[3].length() > 1){
                    String[] arr = s[3].split(";");
                    for (String x :arr) {
                        String[] newArr = x.split(",");
                        Service service = new Service(newArr[0], Integer.parseInt(newArr[1]));
                        services.add(service);
                    }
                }
                Computer com = new Computer(s[0], s[1].equals("Avaiable"), date,services);
                listComputer.add(com);
            }
        } catch (IOException | ParseException e) {
            listComputer = new ArrayList<>();
        }

    }

    private void writeFile(){
        String s = "";
        for (Computer u : listComputer){
            s += u +"\n";
        }
        readWriteFile.wirteFile(path, s);
    }

    public ManageComputer() {
        readFile();
    }

    public ManageComputer(ArrayList<Computer> listComputer) {
        this.listComputer = listComputer;
    }

    public ArrayList<Computer> getListComputer() {
        return listComputer;
    }
    public Computer getItemListComputer(int position) {
        return listComputer.get(position);
    }

    public int size(){
        return listComputer.size();
    }

    public void setListComputer(ArrayList<Computer> listComputer) {
        this.listComputer = listComputer;
    }

    public void setItemList(int position, boolean status, Date date){
        listComputer.get(position).setStatus(status);
        listComputer.get(position).setServices(new ArrayList<>());
        listComputer.get(position).setStartTime(date);
        writeFile();
    }

    public void addItemListComputer(Computer com){
        listComputer.add(com);
        writeFile();
    }

    public void editItemListComputer(int i, Computer com){
        listComputer.get(i).setNameComputer(com.getNameComputer());
        listComputer.get(i).setStatus(com.getStatus());
        listComputer.get(i).setStartTime(com.getStartTime());
        listComputer.get(i).setServices(com.getServices());

        writeFile();

    }

    public void deleteItemListComputer(int i){
        i--;
        listComputer.remove(i);
       writeFile();
    }

    public void printListComputer(){
        int i = 0;
        for(Computer com : listComputer){
            i++;
            System.out.println(i + ": " + com.getNameComputer() + " - "
                + (com.getStatus()== true?"Avaiable":"Disable"));
        }
    }

    public ArrayList<Computer> listComputerChoice(boolean choice){
        ArrayList<Computer> list = new ArrayList<>();
        for(Computer com : listComputer){
            if(com.getStatus() == choice) {
                list.add(com);
            }
        }
        return list;
    }

    public void printItemListComputer(int i){
        i--;
        System.out.println("Machine name: " + listComputer.get(i-1).getNameComputer() + " - "
                + (listComputer.get(i).getStatus()== true? listComputer.get(i).sumMoney() :"The device is offline"));
    }

    public void swap(int i, int j){
        i--;j--;
        Computer x = listComputer.get(i);
        listComputer.set(i, listComputer.get(j));
        listComputer.set(j, x);

        writeFile();
    }

    public int findItemListComputer(String name){
        int i = 0;
        for(Computer x : listComputer){
            if(x.getNameComputer().equals(name)) return i;
            i++;
        }
        return -1;
    }


}
