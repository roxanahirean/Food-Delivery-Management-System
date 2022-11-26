package business;


import com.opencsv.bean.CsvToBeanBuilder;
import dataLayer.*;
import presentation.EmployeeGUI;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;



import java.io.FileReader;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private int idPers;
    private Map<Order, List<MenuItem>> orders;
    private Set<MenuItem> menu;
    private Serializator ser = new Serializator();
    private HashMap<String, User> users;
    private int clientID = 1;
    private int adminID = 1;
    private int employeeID = 1;
    private EmployeeGUI employeeGUI = new EmployeeGUI(this);

    Serializator serializator = new Serializator();

    public DeliveryService(){

        try{
            this.users = (HashMap<String, User>) ser.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\client.ser");
            Client.setClientID(users.size());
        } catch (NullPointerException e){
            this.users = new HashMap<>();
            Client.setClientID(0);
        }

        try{
            this.orders = (Map<Order, List<MenuItem>>) ser.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\order.ser");
            Order.setId(orders.size());
        } catch (NullPointerException e){
            this.orders = new HashMap<>();
            Order.setId(0);
        }

        idPers = 0;
        importProducts();
        employeeGUI.setVisible(false);
    }

    public void setIdPers ( int id){
        this.idPers = id;
    }

    public Set<MenuItem> getMenu () {
        return this.menu;
    }


    // IMPORT

    public void importProducts()  {
        assert isWellFormed();
        List<BaseProduct> baseProducts = null;
        try {

             baseProducts = new CsvToBeanBuilder(new FileReader("products.csv"))
                    .withType(BaseProduct.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        menu = (Set<MenuItem>) ser.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser");

        if(menu == null) {
            menu = baseProducts.stream().filter(distinctByKey(BaseProduct::getTitle))
                    .collect(Collectors.toSet());
        }
        menu.forEach(x -> System.out.println(x.toString() + "\n ... \n"));

        assert menu!=null;
    }



    //MANAGE PRODUCTS

    public void addProd(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        assert isWellFormed();
        assert title!=null;
        int s = menu.size();
        menu.add(new BaseProduct(title, rating, calories, protein, fat, sodium, price));
        System.out.println("\nProdusul '" + title + "' a fost adaugat.\n");
        assert menu.size() == s+1;
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser", menu);
        menu = (Set<MenuItem>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser");
    }

    public void addProd(MenuItem menuItem){
        menu.add(menuItem);
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser", menu);
        menu  = (Set<MenuItem>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser");
    }

    public void updateProd(String title, String rating, String calories, String protein, String fat, String sodium, String price) {
        BaseProduct b = null;
        for (MenuItem m : menu) {
            if (m.getTitle().equals(title) && (m instanceof BaseProduct)) {
                b = (BaseProduct) m;
                break; } }
        if (b != null) {
            if (rating != null)
                b.setRating(Integer.parseInt(rating));
            if (calories != null)
                b.setCal(Integer.parseInt(calories));
            if (protein != null)
                b.setProt(Integer.parseInt(protein));
            if (fat != null)
                b.setFat(Integer.parseInt(fat));
            if (sodium != null)
                b.setSodium(Integer.parseInt(sodium));
            if (price != null)
                b.setPrice(Integer.parseInt(price));
            System.out.println("\nProdusul a fost modificat.\n");
        }
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser", menu);
        menu = (Set<MenuItem>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser");

    }


    public void delProd(String str) {
        assert isWellFormed();
        assert str!=null;
        int si = menu.size();
        List<MenuItem> b = menu.stream().filter(s -> s.getTitle().equals(str)).collect(Collectors.toList());
        for (MenuItem m : b) {
            menu.remove(m);
            System.out.println("\nProdusul '" + m.getTitle() + "' a fost sters.\n");
        }
        assert si >= menu.size();
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser", menu);
        menu = (Set<MenuItem>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser");

    }

    public void addCompProd(CompositeProduct compProd) {
        menu.add(compProd);
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser", menu);
        menu = (Set<MenuItem>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\menu.ser");

    }



    //GENERARE RAPOARTE

    public List<Order> generateRapH(String h1, String h2) {           // Produsele comandate intre orele specificate
        assert isWellFormed();
        assert h1 != null;
        assert h2 != null;
        List<Order> o = new ArrayList<>();
        for (Order or : orders.keySet()) {
            String s = Character.toString(or.getDate().charAt(14)) + Character.toString(or.getDate().charAt(15));
            if ((Integer.parseInt(s) >= Integer.parseInt(h1)) && (Integer.parseInt(s) <= Integer.parseInt(h2))) {
                o.add(or);
            }
        }
        assert o.size() < orders.size();
        return o;
    }
    public String generateRapVal(String nr, String val) {      // clientii care au comandat de mai mult de un numar specificat de ori produse cu o valoare mai mare decat cea specificata
        ArrayList<User> users = (ArrayList<User>) this.users.values().stream()
                .filter(user -> this.orders.keySet().stream()
                        .filter(order -> this.orders.get(order).stream()
                                .mapToDouble(MenuItem::computePrice).reduce(0, Double::sum) >= Integer.parseInt(val))
                        .count() >= Integer.parseInt(nr))
                .collect(Collectors.toList());

        StringBuilder text = new StringBuilder();
        for(User user: users){
            text.append(user.getUsername()).append("\n");
        }
        return text.toString();
    }


    public String generateRapNr(String nr) {           //Produse comandate de mai mult de un numar specificat de ori
        assert nr!=null;
        ArrayList<MenuItem> duplicatedList = new ArrayList<>();

        for(Map.Entry<Order, List<MenuItem>> mapEntry: this.orders.entrySet()){
            duplicatedList.addAll(mapEntry.getValue());
        }

        ArrayList<MenuItem> productsForReport = (ArrayList<MenuItem>) duplicatedList.stream().filter(p -> Collections.frequency(duplicatedList, p) >= Integer.parseInt(nr)).distinct().collect(Collectors.toList());

        StringBuilder text = new StringBuilder();
        for(MenuItem menuItem: productsForReport){
            text.append(menuItem.getTitle()).append("\n");
        }
        return text.toString();
    }
    public List<Order> generateRapDay(String day) {        // comenzile plasate intr o anumita zi
        List<Order> o = new ArrayList<>();
        for (Order or : orders.keySet()) {
            String s = or.getDate().charAt(8) + Character.toString(or.getDate().charAt(9));
            if (Integer.parseInt(s) == Integer.parseInt(day)) {
                o.add(or);
            }
        }
        return o;
    }


    // REGISTER

    public void Register(String username, String password, int usertype) {
        if(users.get(username) == null) {
            if (usertype == 1) {
                Administrator admin = new Administrator(username, password, adminID++);
                users.put(username, admin);
            } else {
                if (usertype == 2) {
                    Client client = new Client(username, password, clientID++);
                    client.setOrdersNr(client.getOrdersNr()+1);
                    users.put(username, client);
                } else {
                    if (usertype == 3) {
                        Employee empl = new Employee(username, password, employeeID++);
                        users.put(username, empl);
                    }
                }
            }
        }
        else{
            JFrame jf1= new JFrame();
            jf1.setSize(200,200);
            JTextField jt1 = new JTextField("User already exists!");
            jf1.setContentPane(jt1);
            jf1.setVisible(true);
        }
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\client.ser", users);
        users = (HashMap<String, User>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\client.ser");
    }



    public int Login(String username, String password) {
        if(users.get(username) != null) {
            if (users.get(username).getPassword().equals(password)) {
                if (users.get(username) instanceof Administrator) {
                    return 1;

                } else if (users.get(username) instanceof Client) {
                    return 2;

                } else {
                    if(users.get(username) instanceof Employee) {
                        return 3;
                    }
                }
            } else {
                JFrame jf1 = new JFrame("ERROR");
                JLabel messageError = new JLabel("Incorrect password!");
                jf1.setContentPane(messageError);
                jf1.setSize(100,100);
                jf1.setVisible(true);
            }
        } else{
            JFrame jf1 = new JFrame("ERROR");
            JLabel messageError = new JLabel("User does not exist!");
            jf1.setContentPane(messageError);
            jf1.setSize(100,100);
            jf1.setVisible(true);
        }
        serializator.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\client.ser", users);
        users = (HashMap<String, User>) serializator.deserialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\client.ser");
        return 0;
    }


    // SEARCH

    public List<MenuItem> searchTitle(String t) {
        assert isWellFormed();
        assert !t.isEmpty();
        List<MenuItem> m = menu.stream()
                .filter(x -> x.getTitle().contains(t))
                .collect(Collectors.toList());
        assert m.get(0).getTitle().contains(t);
        return m;
    }
    public List<MenuItem> searchPrice(String t) {
        return menu.stream()
                .filter(x -> x.computePrice() == Integer.parseInt(t))
                .collect(Collectors.toList());
    }



    public List<MenuItem> searchRating(String t) {
        assert isWellFormed();
        assert  t.length() == 0;
        List<MenuItem> m = menu.stream()
                .filter(x -> {
                    if (x instanceof BaseProduct)
                        return ((BaseProduct) x).getRating() == Float.parseFloat(t);
                    return false;
                })
                .collect(Collectors.toList());
        assert ((BaseProduct)m.get(0)).getCalories() == Integer.parseInt(t);
        return m;
    }
    public List<MenuItem> searchCalories(String t) {
        return menu.stream()
                .filter(x -> (x instanceof BaseProduct) && (((BaseProduct) x).getCalories() == Integer.parseInt(t)))
                .collect(Collectors.toList());
    }


    // CREATE ORDER

    public void createOrder(List<MenuItem> items) {
        assert isWellFormed();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'la' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        int siz = orders.size();
        int price = 0;
        for (MenuItem m : items)
            price += m.computePrice();
        Order o = new Order(idPers, formatter.format(date), price, items);
        orders.put(o, items);
        notifyUpdate(o);
        employeeGUI.update(o);
        employeeGUI.setVisible(true);
        FWriter fileWriter = new FWriter();

        System.out.println(o);
        ser.serialize("D:\\TP\\PT2022_30224_Hirean_Roxana_Assignment_4\\order.ser", orders);

        for (Order or : orders.keySet()) {
            String s = or.toString();
            for (MenuItem m : orders.get(or))
                s = s + "|       -" + m.getTitle() + ";    PRET: " + m.computePrice() + "\n";
            s += "|\n| TOTAL: " + or.getPrice() + "\n|_______________________________________________________________________________\n\n\n\n";
            fileWriter.writeText(s);
        }
        fileWriter.closeFW();
        assert orders.size() > siz;
    }


    //WELL FORMED

    protected boolean isWellFormed() {
        if(users == null || orders == null || menu == null)
            return false;
        return true;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, String> seen = new ConcurrentHashMap<>();
        return t -> seen.put(keyExtractor.apply(t), "") == null;
    }
}
