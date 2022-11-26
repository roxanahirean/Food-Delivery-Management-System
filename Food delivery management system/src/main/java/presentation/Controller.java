package presentation;

import business.DeliveryService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class Controller extends Observable {
    private MainGUI mainG = new MainGUI();
    private DeliveryService deliverys = new DeliveryService();
    public Controller(){
        mainG.addRegisterButton(new registerButton());
        mainG.addSubmitButton(new submitButton());
    }

    class submitButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String user = mainG.getUserText().getText();
            String password = mainG.getPasswordText().getText();

            int type = deliverys.Login(user,password);
            if(type == 1){
                AdministratorGUI admnGUI = new AdministratorGUI(deliverys);
            } else{
                if(type == 2){
                    ClientGUI clGUI = new ClientGUI(deliverys);
                } else{
                    if(type == 3){
                        EmployeeGUI empGUY = new EmployeeGUI(deliverys);
                    }
                }
            }

        }
    }

    class registerButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = mainG.getUserText().getText();
            String password = mainG.getPasswordText().getText();
            if(mainG.getSelectUser().isSelected()){
                deliverys.Register(user,password,1);
            } else
            if(mainG.getSelectUser1().isSelected()){
                deliverys.Register(user,password,2);
            } else {
                deliverys.Register(user,password,3);
            }
        }
    }

}
