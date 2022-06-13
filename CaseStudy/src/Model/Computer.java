package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Computer {
    private String nameComputer;
    private boolean status;
    private Date startTime;
    private ArrayList<Service> services;

    private int MONEY = 5000;

    public Computer() {
    }

    public Computer(String nameComputer) {
        this.nameComputer = nameComputer;
        this.status = false;
        this.startTime = new Date();
        this.services = new ArrayList<>();
    }


    public Computer(String nameComputer, Boolean status) {
        this.nameComputer = nameComputer;
        this.status = status;
        this.startTime = new Date();
        this.services = new ArrayList<>();
    }

    public Computer(String nameComputer, Boolean status, Date useTime, ArrayList<Service> service) {
        this.nameComputer = nameComputer;
        this.status = status;
        this.startTime = useTime;
        this.services = service;
    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = nameComputer + '-' + (status?"Avaiable":"Disable") + '-' +
                sdf.format(startTime.getTime()) + "-";
        if(!services.isEmpty()) {
            s += services.get(0);
            for (int i = 1; i < services.size(); i++) {
                s += ";" + services.get(i);
            }
        }
        else s+="0";
        return s;

//        return nameComputer + '-' + (status?"Avaiable":"Disable") + '-' +
//                startTime.getTime() + "-" + moneyService;
    }

    public String getNameComputer() {
        return nameComputer;
    }

    public void setNameComputer(String nameComputer) {
        this.nameComputer = nameComputer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> money) {
        this.services = money;
    }
    public void addService(Service service){
        this.services.add(service);
    }

    public int getHour(){
        return Calendar.getInstance().getTime().getHours() - startTime.getHours();
    }
    public int sumMoney(){
        int hour = getHour();
        int money = 0;
        if(!services.isEmpty()){
            for (Service x: services) {
                money += x.getPrice();
            }
        }
        return hour * this.MONEY + money;
    }

    public int getMONEY() {
        return MONEY;
    }

    public void setMONEY(int MONEY) {
        this.MONEY = MONEY;
    }
}
