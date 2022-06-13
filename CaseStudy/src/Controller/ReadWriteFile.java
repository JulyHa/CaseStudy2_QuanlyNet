package Controller;

import Model.Computer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFile {
    public ArrayList<String> readFile(String path) throws IOException {
        ArrayList<String> x = new ArrayList<>();
        try {
            File f = new File(path);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                x.add(line);
            }
            fr.close();
            br.close();
            return x;
        } catch (IOException e) {
            e.printStackTrace();
            return x;
        }
    }

    public boolean wirteFile(String path, String x) {
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            fw.write(x.toString());
            fw.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            return false;
        }
    }
}
