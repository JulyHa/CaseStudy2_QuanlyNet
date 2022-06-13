package Model;

import Controller.ReadWriteFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManageTurnover {
    private ArrayList<Turnover> turnovers = new ArrayList<>();

    final ReadWriteFile readWriteFile = new ReadWriteFile();
    final static String path = "D:\\Uyen\\TaiLieuDiLam\\CodeGym\\CodeJava\\CaseStudy\\src\\Data\\Turnover.txt";

    private void readFile(){
        try {
            ArrayList<String>list = readWriteFile.readFile((path));
            for (String line : list){
                String[] s = line.split("-");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = (Date)sdf.parse(s[0]);
                Turnover turnover = new Turnover(date, Integer.parseInt(s[1]));
                this.turnovers.add(turnover);
            }
        } catch ( IOException | ParseException e) {
            this.turnovers = new ArrayList<>();
        }
    }

    private void writeFile(){
        String s = "";
        for (Turnover turnover : turnovers){
            s+= turnover +"\n";
        }
        readWriteFile.wirteFile(path, s);
    }

    public ManageTurnover() {
        readFile();
    }

    public ManageTurnover(ArrayList<Turnover> turnovers) {
        this.turnovers = turnovers;
    }

    public ArrayList<Turnover> getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(ArrayList<Turnover> turnovers) {
        this.turnovers = turnovers;
    }

    public void addItemTurnovers(Turnover turnover){
        this.turnovers.add(turnover);
        writeFile();
    }
    public int getTotalTurnover(){
        int total = 0;
        for (Turnover t :turnovers ) {
            total += t.getMoney();
        }
        return total;
    }
    public ArrayList<Turnover> getTotalDate(Date start, Date end){
        ArrayList<Turnover> arr = new ArrayList<>();
        for (Turnover t :turnovers ) {
            if(t.getDate().compareTo(start) > 0 && t.getDate().compareTo(end) < 1){
                arr.add(t);
            }

        }
        return arr;
    }
}
