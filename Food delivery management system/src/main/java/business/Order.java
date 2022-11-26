package business;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Serializable {
    private int id;
    private String date;
    private int clientId;
    private int price;
    private static AtomicInteger nr = new AtomicInteger(0);
    private java.util.List<MenuItem> menuItems;

    public Order(int clientId, String date, int price, List<MenuItem> menuItems){
        this.id = nr.get();
        nr.getAndIncrement();
        this.date = date;
        this.clientId = clientId;
        this.price = price;
        this.menuItems = menuItems;
    }

    public static void setId(int id) {
        nr.getAndSet(id);
    }

    public int getPrice(){
        return price;
    }
    public String getDate(){
        return date;
    }

    public String toString1(){
        return "ID comanda: " + id +" ID Client: " + clientId + " Data: " + date + " Valoare: "+ price;
    }

    public int hashCode(){
        return id;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("_______________________________________________________________________________\n| ID comanda: " + id + "\n| ID Client: " + clientId + "\n| Plasata in data de: " + date + "\n| Contine: \n");
        for(MenuItem menuItem: menuItems){
            str.append(menuItem.getTitle()).append("\n");
        }
        return str.toString();
    }

    public int getClientId() {
        return this.clientId;
    }
}
