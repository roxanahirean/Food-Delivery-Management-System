package dataLayer;

import java.io.FileWriter;
import java.io.IOException;

public class FWriter {

    FileWriter fileWriter;

    public FWriter(){
        try {
            fileWriter = new FileWriter("C:\\Users\\ROXY\\Desktop\\file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeText(String s ){
        try {
            fileWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFW(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
