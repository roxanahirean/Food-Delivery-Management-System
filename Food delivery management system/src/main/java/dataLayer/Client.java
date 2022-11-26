package dataLayer;

import java.io.Serializable;


public class Client extends User implements Serializable {
    private static int clientID;
    //private int clientID;
    private int ordersNr;

    public Client(){
        super();
    };

    public Client(String username,String password) {
        super(username, password);
    }

    public Client(String username, String password, int clientID) {
        super(username, password);
        this.clientID = clientID;
    }

    public static void setClientID(int clientIDD) {
        clientID = clientIDD;
    }

    public int getOrdersNr() {
        return ordersNr;
    }

    public void setOrdersNr(int ordersNr) {
        this.ordersNr = ordersNr;
    }

    public int getClientID() {
        return clientID;
    }
}
