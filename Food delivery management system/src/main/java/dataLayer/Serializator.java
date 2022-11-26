package dataLayer;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serializator implements Serializable{

        public void serialize(String fileName, Object obj){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.close();
            } catch (IOException e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Output stream file not able to be opened!");
                e.printStackTrace();
            }
        }

        public Object deserialize(String filename){
            Object obj = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                obj = objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            } catch (EOFException e){
                Logger.getAnonymousLogger().log(Level.FINE, "File has ended!");
            } catch (IOException | ClassNotFoundException e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Input stream file not able to be opened!");
            }
            return obj;
        }
    }


