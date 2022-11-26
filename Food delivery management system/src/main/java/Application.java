
import business.DeliveryService;
import presentation.*;

public class Application {
    public static void main(String[] args) {
        DeliveryService d = new DeliveryService();
       // EmployeeGUI observer = new EmployeeGUI(d);
        //Client c = new Client("a", "b");
        //AdministratorGUI a = new AdministratorGUI(d);
        //MainGUI m = new MainGUI();
        Controller c = new Controller();

    }
}
