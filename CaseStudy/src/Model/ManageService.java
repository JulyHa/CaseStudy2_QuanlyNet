package Model;

import Controller.ReadWriteFile;

import java.io.IOException;
import java.util.ArrayList;

public class ManageService {

    private ArrayList<Service> listService = new ArrayList<>();

    final ReadWriteFile readWriteFile = new ReadWriteFile();
    final static String path = "D:\\Uyen\\TaiLieuDiLam\\CodeGym\\CodeJava\\CaseStudy\\src\\Data\\Service.txt";

    public ManageService() {
        readFile();
    }

    public ArrayList<Service> getListService() {
        return listService;
    }

    public void setListService(ArrayList<Service> listService) {
        this.listService = listService;
    }

    private void readFile(){
        try {
            ArrayList<String>list = readWriteFile.readFile((path));
            for (String line : list){
                String[] s = line.split(",");
                Service service = new Service(s[0], Integer.parseInt(s[1]));
                listService.add(service);
            }
        } catch ( IOException e) {
            listService = new ArrayList<>();
        }
    }

    private void writeFile(){
        String s = "";
        for (Service service : listService){
            s+= service +"\n";
        }
        readWriteFile.wirteFile(path, s);
    }

    public int size(){
        return listService.size();
    }

    public Service getItemListServece(int pos){
        pos--;
        return listService.get(pos);
    }

    public void addItemListService(Service service){
        listService.add(service);
        writeFile();
    }

    public void editItemListService(int i, Service service){
        i--;
        listService.get(i).setNameService(service.getNameService());
        listService.get(i).setPrice(service.getPrice());

        writeFile();

    }

    public void deleteItemListService(int i){
        i--;
        listService.remove(i);
        writeFile();
    }

    public void printListService(){
        int i = 0;
        for(Service service : listService){
            i++;
            System.out.println(i + ": " + service.getNameService() + " - " +service.getPrice()+"VND");
        }
    }

    public void printItemListService(int i){
        i--;
        System.out.println("Service name: " + listService.get(i).getNameService() + " - " +listService.get(i).getPrice() +"VND");
    }

    public int findItemListService(String fullname) {
        int index = 0;
        for (Service s :listService) {
            index++;
            if (s.getNameService().equals(fullname)) return index;
        }
        return -1;
    }

}
