package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Computer {
    private String nameComputer;
    private Boolean status;
    private Date startTime;
    private int moneyService;

    final static int MONEY = 2000;

    public Computer() {
    }

    public Computer(String nameComputer) {
        this.nameComputer = nameComputer;
        this.status = false;
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        try {
//            Date date = (Date)formatter.parse(String.valueOf(startTime));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        this.startTime = new Date();
        this.moneyService = 0;
    }


    public Computer(String nameComputer, Boolean status) {
        this.nameComputer = nameComputer;
        this.status = status;
        this.startTime = new Date();
        this.moneyService = 0;
    }

    public Computer(String nameComputer, Boolean status, Date useTime, int money) {
        this.nameComputer = nameComputer;
        this.status = status;
        this.startTime = useTime;
        this.moneyService = money;
    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return nameComputer + '-' + (status?"Avaiable":"Disable") + '-' +
                sdf.format(startTime.getTime()) + "-" + moneyService;

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

    public int getMoneyService() {
        return moneyService;
    }

    public void setMoneyService(int money) {
        this.moneyService = money;
    }

    public int sumMoney(){
        int hour = Calendar.getInstance().getTime().getHours() - startTime.getHours();
        return hour * MONEY + moneyService;
    }
}
