package FOS_DATA;

import FOS_CORE.Customer;
import FOS_CORE.Discount;
import FOS_CORE.MenuItem;
import FOS_CORE.Restaurant;

import java.sql.*;
import java.util.ArrayList;

public class RestaurantData implements IRestaurantData{

    public ArrayList<Discount> fetchMenuItemDiscounts(MenuItem menuItem) {
        int menuItemID = menuItem.getMenuItemID();
        ArrayList<Discount> discounts = new ArrayList<>();
        final String sql = "SELECT * FROM Discount WHERE menu_item_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menuItemID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int discountID = resultSet.getInt("discount_id");
                String discountName = resultSet.getString("discount_name");
                String discountDescription = resultSet.getString("discount_description");
                double percentage = resultSet.getDouble("discount_percentage");
                Timestamp startDate = resultSet.getTimestamp("start_date");
                Timestamp endDate = resultSet.getTimestamp("end_date");
                long millis= System.currentTimeMillis();
                Date currentDate = new Date(millis);
                if (startDate.before(currentDate) && endDate.after(currentDate)) {
                    Discount discount = new Discount(discountID, discountName, discountDescription, percentage, startDate, endDate);
                    discounts.add(discount);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database failed to fetch Menu Item Discounts: " + e.getMessage());
        }
        return discounts;
    }

    public ArrayList<Restaurant> fetchRestaurantsByCity(String city) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT restaurant_id, name, cuisine_type, city, manager_id FROM Restaurant WHERE city = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int restaurantId = resultSet.getInt("restaurant_id");
                String name = resultSet.getString("name");
                String cuisineType = resultSet.getString("cuisine_type");
                Restaurant restaurant = new Restaurant(restaurantId, name, cuisineType, city);
                restaurants.add(restaurant);
            }
            return restaurants;
        } catch (SQLException e) {
            System.out.println("Database failed to fetch restaurants by city: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<MenuItem> fetchRestaurantMenu(Restaurant restaurant) {
        int restaurantId = restaurant.getRestaurantID();
        ArrayList<MenuItem> menu = new ArrayList<>();
        final String sql = "SELECT menu_item_id, item_name, description, price FROM MenuItem WHERE restaurant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int menuItemId = resultSet.getInt("menu_item_id");
                String itemName = resultSet.getString("item_name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                MenuItem menuItem = new MenuItem(menuItemId, itemName, description, price);
                menu.add(menuItem);
            }
            return menu;
        } catch (SQLException e) {
            System.out.println("Database failed to fetch restaurant menu items: " + e.getMessage());
        }
        return null;
    }

    @Override
    public double calculateRestaurantRating(Restaurant restaurant) {
        int restaurantID = restaurant.getRestaurantID();
        final String sql = " SELECT AVG(rt.rating_value) as avg_rating " +
                "FROM Rating rt JOIN `Order` o ON rt.order_id = o.order_id WHERE o.restaurant_id = ? ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, restaurantID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("avg_rating");
            }

        }catch (SQLException e) {
            System.out.println("Database failed to fetch restaurant ratings: " + e.getMessage());
        }
        return 0;
    }
}
