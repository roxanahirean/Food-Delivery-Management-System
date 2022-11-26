package presentation;

import business.CompositeProduct;
import business.DeliveryService;
import business.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdministratorGUI extends JFrame{
    private final JPanel panel1 = new JPanel();
    private final JPanel p1 = new JPanel();
    private final JPanel pComp1 = new JPanel();
    private final JPanel pRap4 = new JPanel();
    private final JTextField titleComp = new JTextField(15);
    private final JTextField title = new JTextField(15);
    private final JTextField rating = new JTextField(10);
    private final JTextField calories = new JTextField(10);
    private final JTextField proteins = new JTextField(10);
    private final JTextField fats = new JTextField(10);
    private final JTextField sodium = new JTextField(10);
    private final JTextField price = new JTextField(10);
    private final JTextField priceComp = new JTextField(10);
    JComboBox menuItems = new JComboBox();
    private List<business.MenuItem> items = new ArrayList<business.MenuItem>();
    private java.util.List<Order> orderList = new ArrayList<>();
    private List<MenuItem> itemsRap;
    DeliveryService ds;

    public AdministratorGUI(DeliveryService deliveryService) {
        ds = deliveryService;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2,-80, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 30, 100));
        panel.add(new JLabel("DENUMIRE:"));
        panel.add(title);
        panel.add(new JLabel("RATING:"));
        panel.add(rating);
        panel.add(new JLabel("CALORII:"));
        panel.add(calories);
        panel.add(new JLabel("PROTEINE:"));
        panel.add(proteins);
        panel.add(new JLabel("GRASIMI:"));
        panel.add(fats);
        panel.add(new JLabel("SODIU:"));
        panel.add(sodium);
        panel.add(new JLabel("PRET:"));
        panel.add(price);
        panel1.add(panel);
        JButton button = new JButton("ADAUGARE");
        JButton button1 = new JButton("STERGERE");
        JButton button2 = new JButton("MODIFICARE");
        JButton back = new JButton("<=");
        back.addActionListener(new bListener(this));
        button.addActionListener(new addListener());
        button1.addActionListener(new delListener());
        button2.addActionListener(new updateListener());
        panel1.add(button);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(back);

        JPanel pComp = new JPanel();
        pComp.setLayout(new GridLayout(0, 2,-185, 10));
        pComp.setBorder(BorderFactory.createEmptyBorder(90, 0, 12, 0));
        pComp.add(new JLabel("DENUMIRE:"));
        pComp.add(titleComp);
        pComp.add(new JLabel("PRET:"));
        pComp.add(priceComp);
        pComp.add(new JLabel("ADAUGA PRODUS:"));
        pComp.add(menuItems);
        menuItems.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -300));
        pComp1.add(pComp);
        JButton button3 = new JButton("ADAUGARE");
        JButton back1 = new JButton("<=");
        JButton button4 = new JButton("FINALIZARE");
        back1.addActionListener(new bListener(this));
        button3.addActionListener(new addProdCompListener());
        button4.addActionListener(new finalProdCompListener());
        pComp1.add(button3);
        pComp1.add(button4);
        pComp1.add(back1);




        JPanel pRap = new JPanel();
        JPanel pRap1 = new JPanel();
        JPanel pRap2 = new JPanel();
        JPanel pRap3 = new JPanel();
        // pComp.setBorder(BorderFactory.createEmptyBorder(90, 0, 12, 0));
        pRap.add(new JLabel("Ora incepu:"));
        JTextField h1 = new JTextField(3);
        pRap.add(h1);
        pRap.add(new JLabel("Ora sfarsit:"));
        JTextField h2 = new JTextField(5);
        JButton a1 = new JButton("AFISARE");
        pRap.add(h2);
        pRap.add(a1);

        pRap1.add(new JLabel("Numar comenzi-produs:"));
        JTextField nrc = new JTextField(10);
        pRap1.add(nrc);
        JButton a2 = new JButton("AFISARE");
        pRap1.add(a2);

        pRap2.add(new JLabel("Numar comenzi-client:"));
        JTextField nrc1 = new JTextField(3);
        pRap2.add(nrc1);
        pRap2.add(new JLabel(" Valoare:"));
        JTextField val = new JTextField(3);
        JButton a3 = new JButton("AFISARE");
        pRap2.add(val);
        pRap2.add(a3);

        pRap3.add(new JLabel("Zi:"));
        JTextField day = new JTextField(3);
        pRap3.add(day);
        JButton a4 = new JButton("AFISARE");
        JButton back6 = new JButton("<=");
        back6.addActionListener(new bListener(this));
        pRap3.add(a4);
        pRap3.add(back6);

        JTextArea textArea1 = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(450, 190));
        scrollPane1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        a1.addActionListener(new afisOraListener(h1, h2, textArea1, this));
        a2.addActionListener(new afisNrListener(nrc, textArea1, this));
        a3.addActionListener(new afisNrValListener(nrc1, val, textArea1, this));
        a4.addActionListener(new afisZiListener(day, textArea1, this));

        pRap4.add(pRap);
        pRap4.add(pRap1);
        pRap4.add(pRap2);
        pRap4.add(pRap3);
        pRap4.add(scrollPane1);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1,10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
        JButton b = new JButton("IMPORTARE MENIU");
        JButton b1 = new JButton("ADMINISTRARE PRODUSE");
        JButton b2 = new JButton("CREARE PRODUS COMPUS");
        JButton b3 = new JButton("GENERARE RAPOARTE");
        b.addActionListener(new importListener());
        b1.addActionListener(new adminListener(this));
        b2.addActionListener(new compListener(this));
        b3.addActionListener(new generateListener(this));
        p.add(b);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p1.add(p);
        this.setContentPane(p1);
        this.pack();
        this.setTitle("ADMINISTRATOR");
        this.setVisible(true);
        this.setBounds(100,100,514,435);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public class adminListener implements ActionListener {
        JFrame f;
        public adminListener(JFrame f) {
            this.f = f;
        }
        public void actionPerformed(ActionEvent e) {
            f.setContentPane(panel1);
            f.revalidate();
        }
    }

    public class bListener implements ActionListener {
        JFrame f;
        public bListener(JFrame f) {
            this.f = f;
        }
        public void actionPerformed(ActionEvent e) {
            f.setContentPane(p1);
            f.revalidate();
        }
    }

    public class compListener implements ActionListener {
        JFrame f;
        public compListener(JFrame f) {
            this.f = f;
        }
        public void actionPerformed(ActionEvent e) {
            items = new ArrayList<business.MenuItem>();
            for(business.MenuItem m:ds.getMenu()) {
                menuItems.addItem(m.getTitle());
            }
            f.setContentPane(pComp1);
            f.revalidate();
        }
    }
    public class generateListener implements ActionListener {
        JFrame f;
        public generateListener(JFrame f) {
            this.f = f;
        }
        public void actionPerformed(ActionEvent e) {

            f.setContentPane(pRap4);
            f.revalidate();
        }
    }

    public class addListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ds.addProd(title.getText(), Float.parseFloat(rating.getText()), Integer.parseInt(calories.getText()), Integer.parseInt(proteins.getText()), Integer.parseInt(fats.getText()), Integer.parseInt(sodium.getText()), Integer.parseInt(price.getText()));
        }
    }


    public class delListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ds.delProd(title.getText());
        }
    }

    public class updateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ds.updateProd(title.getText(), rating.getText(), calories.getText(), proteins.getText(), fats.getText(), sodium.getText(), price.getText());
        }
    }




    public class addProdCompListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<business.MenuItem> itemToAdd = ds.getMenu().stream().filter(menuItem -> menuItem.getTitle().equals(menuItems.getSelectedItem())).collect(Collectors.toList());
            items.add(itemToAdd.get(0));
        }
    }

    public class finalProdCompListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CompositeProduct product = new CompositeProduct(titleComp.getText(), Integer.parseInt(priceComp.getText()), items);
            ds.addCompProd(product);
            items = new ArrayList<>();
        }
    }





    public class importListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ds.importProducts();
        }
    }


    public class afisOraListener implements ActionListener {
        private JTextField tf;
        private JTextField tf1;
        private JTextArea ta;
        private JFrame f;
        public afisOraListener(JTextField text, JTextField tf1,JTextArea ta, JFrame f){
            this.tf = text;
            this.tf1 = tf1;
            this.ta = ta;
            this.f = f; }
        public void actionPerformed(ActionEvent e) {
            orderList.clear();
            orderList = ds.generateRapH(tf.getText(), tf1.getText());
            String s = "";
            for(Order o:orderList)
                s+= o.toString1() + "\n";
            ta.setText(s);
            f.revalidate();
        }
    }

    public class afisZiListener implements ActionListener {
        private JTextField tf;
        private JTextArea ta;
        private JFrame f;
        public afisZiListener(JTextField text, JTextArea ta, JFrame f){
            this.tf = text;
            this.ta = ta;
            this.f = f; }
        public void actionPerformed(ActionEvent e) {
            orderList.clear();
            orderList = ds.generateRapDay(tf.getText());
            String s = "";
            for(Order o:orderList)
                s+= o.toString1() + "\n";
            ta.setText(s);
            f.revalidate();
        }
    }
    public class afisNrListener implements ActionListener {
        private JTextField tf;
        private JTextArea ta;
        private JFrame f;
        public afisNrListener(JTextField text, JTextArea ta, JFrame f){
            this.tf = text;
            this.ta = ta;
            this.f = f; }
        public void actionPerformed(ActionEvent e) {
            String s = ds.generateRapNr(tf.getText());
            ta.setText(s);
            f.revalidate();
        }
    }


    public class afisNrValListener implements ActionListener {
        private JTextField tf;
        private JTextField tf1;
        private JTextArea ta;
        private JFrame f;
        public afisNrValListener(JTextField text, JTextField tf, JTextArea ta, JFrame f){
            this.tf = text;
            this.tf1 = tf;
            this.ta = ta;
            this.f = f; }
        public void actionPerformed(ActionEvent e) {
            String s = ds.generateRapVal(tf.getText(), tf1.getText());
            ta.setText(s);
            f.revalidate();
        }


    }


}
