package FOS_DATA;

import java.util.ArrayList;

import FOS_CORE.*;
import FOS_CORE.MenuItem;

public interface IManagerService {

    public boolean saveRestaurantInfo(Restaurant restaurant);

    public boolean addMenuItem(MenuItem menuItem, Restaurant restaurant);
    public boolean updateMenuItem(MenuItem menuItem);// Please make this class take restaurant as an input since menu items can have the same name and ID can't be resolved without knowing the restaurant
    public boolean removeMenuItem(MenuItem menuItem);

    public boolean createDiscount(Discount discount, MenuItem menuItem);

    public ArrayList<Order> fetchRestaurantOrders(Restaurant restaurant);
    public ArrayList<String> fetchRestaurantKeywords(Restaurant restaurant);
    public ArrayList<Restaurant> fetchManagerRestaurants(Manager manager);

}