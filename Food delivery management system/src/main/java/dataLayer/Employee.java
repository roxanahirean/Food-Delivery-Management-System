package dataLayer;

import business.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Employee extends User implements Serializable {
    private int employeeID;

    public Employee(String username, String password) {
        super(username, password);
    }

    public Employee(String username, String password, int employeeID) {
        super(username, password);
        this.employeeID = employeeID;
    }

    public interface Observer{
        void update();

        void update(Observable obj, Object arg);

        void update(Observable obj, Client arg);

        void update(Observable obj, Order arg);
    }

    private List<Observer> observers = new ArrayList<>();
}
