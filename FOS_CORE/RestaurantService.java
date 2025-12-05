package FOS_CORE;

import FOS_DATA.IManagerService;
import FOS_DATA.ManagerService;

import java.util.ArrayList;

public class RestaurantService implements IRestaurantService {
    private final IManagerService DB = new ManagerService();
    @Override
    public ArrayList<Restaurant> getRestaurantsByCity(String city) {
        if (city == null || city.isEmpty()) {
            return new ArrayList<>();
        }
        return DB.fetchRestaurantsByCity(city);
    }

    @Override
    public ArrayList<MenuItem> fetchRestaurantMenu(Restaurant restaurant) {
        // TODO: Implementation
        return null;
    }

    @Override
    public ArrayList<Restaurant> searchRestaurantsByKeyword(String keyword) {
        // TODO: Implementation
        return null;
    }

    public ArrayList<String> fetchRestaurantKeywords(Restaurant restaurant){
        return new ArrayList<>();
    }

    private void loadRestaurantData(Restaurant restaurant) {
        // TODO: Implementation
    }
}