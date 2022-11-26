package presentation;

import business.DeliveryService;
import business.Order;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class EmployeeGUI extends JFrame implements Observer {

    //private final List<JButton> button = new ArrayList<>();
    private final List<JPanel> panel = new ArrayList<>();
    private JTextArea jTextArea = new JTextArea();
    private final JPanel p = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(p);
    private DeliveryService ds;
    private String str = "";

    public EmployeeGUI(DeliveryService d) {
        this.ds = d;
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500, 400));


        p.setLayout(new GridLayout(0, 1));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));


        JPanel p1 = new JPanel();
        //JButton b = new JButton("Vizualizare comenzi");
        //b.addActionListener(new vizListener(this, this));
        p1.setBorder(BorderFactory.createEmptyBorder(150,0,0,0));
        //p1.add(b);
        this.setContentPane(p1);
        this.pack();
        this.setTitle("ANGAJAT");
        this.setVisible(true);
        this.setBounds(100,100,514,436);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public EmployeeGUI() {
//
//    }


    public void update(Order or){
        JPanel order = new JPanel();
        //JButton b = new JButton("FINALIZATA");
        //b.addActionListener(new fListener(b));
        //button.add(b);
        str += or.toString() + "\n";
        jTextArea.setText(str);
        this.add(jTextArea);
        this.revalidate();
        this.pack();
    }

    public void update1(JButton b){
        //int i = button.indexOf(b);
        //p.remove(panel.get(i));
        //panel.remove(i);
        //button.remove(i);
        this.revalidate();
        this.pack();
    }



    public class vizListener implements ActionListener {
        JFrame f;
        Observer o;
        public vizListener(JFrame f, Observer o) {
            this.f = f;
            this.o = o;
        }
        public void actionPerformed(ActionEvent e) {
            ds.addObserver(o);
            f.setContentPane(scrollPane);
            f.revalidate();
        }
    }

}
