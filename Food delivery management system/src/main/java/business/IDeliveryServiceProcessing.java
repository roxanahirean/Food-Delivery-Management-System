package business;

import java.io.IOException;
import java.util.List;

public interface IDeliveryServiceProcessing {

    void importProducts() throws IOException, ClassNotFoundException;

    void addProd(String title, float rating, int calories, int protein, int fat, int sodium, int price);

    void delProd(String str);

    void createOrder(List<MenuItem> items);

    List<Order> generateRapH(String h1, String h2);

    String generateRapNr(String nr);

    public void Register(String username, String password, int usertype);

    List<MenuItem> searchTitle(String t);

    List<MenuItem> searchRating(String t);


}
