package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public abstract int computePrice();
    public abstract String getTitle();

}