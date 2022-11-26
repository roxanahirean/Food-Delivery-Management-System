package business;

import java.util.List;

public class CompositeProduct extends MenuItem{
    private String title;
    private int price;
    private List<MenuItem> items;

    public CompositeProduct(String title, int price, List<MenuItem> items){
        this.title = title;
        this.price = price;
        this.items =items;
    }

    @Override
    public int computePrice() {
        if(price == 0)
            for(MenuItem m : items)
                price = price + m.computePrice();
        return this.price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString(){
        String s = this.title + "\nFormat din: 1." + items.get(0).getTitle();
        for(int i=1; i<items.size(); i++)
            s = s + "\n                   " + (i+1) + "." + items.get(i).getTitle();
        s+= "\nPret: " + price;
        return s;
    }

}
