package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Turnover {
    Date date;
    int money;

    public Turnover() {
    }

    public Turnover(Date date, int money) {
        this.date = date;
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateFormat =  sdf.format(date.getTime());
        return dateFormat +"-" + money;
    }
}
