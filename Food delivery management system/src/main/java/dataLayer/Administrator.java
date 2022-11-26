package dataLayer;

import business.IDeliveryServiceProcessing;
import dataLayer.User;

import java.io.Serializable;

public class Administrator extends User implements Serializable {
    private int adminID;

    public Administrator(String username, String password) {
        super(username, password);
    }

    public Administrator(String username, String password, int adminID){
        super(username, password);
        this.adminID = adminID;
    }


}
