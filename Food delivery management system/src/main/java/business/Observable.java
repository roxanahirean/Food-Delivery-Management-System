package business;

import presentation.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {

   private List<presentation.Observer> observers = new ArrayList<>();

   public void addObserver(presentation.Observer o) {
       observers.add(o);
   }
   public void delObserver(Observer o) {
       observers.remove(o);
   }
   public void notifyUpdate(Order or) {
       for(presentation.Observer o: observers) {
           o.update(or);
       }
   }
}
