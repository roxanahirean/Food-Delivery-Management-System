package presentation;

import business.Order;

public interface Observer {
    void update(Order or);
}
