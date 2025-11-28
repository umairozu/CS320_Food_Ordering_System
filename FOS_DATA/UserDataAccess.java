package FOS_DATA;

import FOS_CORE.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class UserDataAccess extends UserData {
    public static boolean addNewCustomer(User user){
        final String sql = "INSERT INTO User (username, password, user_type) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, "customer");
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Database failed to save user");
        }
        return false;
    }
    public static User getUserByEmail(String email) {
        final String sql = "SELECT user_id, password, user_type FROM User WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("user_id");
                    String password = resultSet.getString("password");
                    String userType = resultSet.getString("user_type");
                    if(userType.equals("manager")){
                        return new Manager(id, email, password);
                    }else{
                        return new Customer(id, email, password);
                    }

                }
            } catch (SQLException e) {
                System.out.println("No user found with the corresponding username");
            }

        } catch (SQLException e) {
            System.out.println("Database failed to fetch user by username");
        }
        return null;
    }
    public static boolean changeUserPassword(User user, String newHashedPassword){
        int userId = user.getUserID();
        final String sql = "UPDATE User SET password = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newHashedPassword);
            statement.setInt(2, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Database failed to update user password");
            return false;
        }
    }

    public static ArrayList<Card> fetchCustomerCards(Customer customer) {
        int customerId = customer.getUserID();
        ArrayList<Card> cards = new ArrayList<>();
        final String sql = "SELECT card_no, expiry_date, cardholder_name FROM Card WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String cardNo = resultSet.getString("card_no");
                    String expiryDate = resultSet.getString("expiry_date");
                    String cardholderName = resultSet.getString("cardholder_name");
                    cards.add(new Card(cardNo, expiryDate, cardholderName));
                }
            } catch (SQLException e) {
                System.out.println("No cards found for the customer");
            }
        } catch (SQLException e) {
            System.out.println("Database failed to fetch customer cards");
        }
        return cards;
    }
    public static boolean addCardToCustomer(Customer customer, Card card) {
        int customerId = customer.getUserID();
        final String sql = "INSERT INTO Card (customer_id, card_no, expiry_date, cardholder_name) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.setString(2, card.getCardNo());
            statement.setString(3, card.getExpiryDate());
            statement.setString(4, card.getCardholderName());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Database failed to add card to customer");
            return false;
        }
    }
    public static ArrayList<Address> fetchCustomerAddresses(Customer customer) {
        int customerId = customer.getUserID();
        ArrayList<Address> addresses = new ArrayList<>();
        final String sql = "SELECT address_line, city, state, zip_code FROM Address WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String addressLine = resultSet.getString("address_line");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zipCode = resultSet.getString("zip_code");
                    addresses.add(new Address(addressLine, city, state, zipCode));
                }
            } catch (SQLException e) {
                System.out.println("No addresses found for the customer");
            }
        } catch (SQLException e) {
            System.out.println("Database failed to fetch customer addresses");
        }
        return addresses;
    }
    public static boolean addAddress(Customer customer, Address address) {
        int customerId = customer.getUserID();
        final String sql = "INSERT INTO Address (customer_id, address_line, city, state, zip_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.setString(2, address.getAddressLine());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setString(5, address.getZipCode());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Database failed to add address to customer");
            return false;
        }
    }
    public static ArrayList<String> fetchCustomerPhoneNumbers(Customer customer) {
        int customerId = customer.getUserID();
        ArrayList<String> phoneNumbers = new ArrayList<>();
        final String sql = "SELECT phone_number FROM PhoneNumber WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String phoneNumber = resultSet.getString("phone_number");
                    phoneNumbers.add(phoneNumber);
                }
            } catch (SQLException e) {
                System.out.println("No phone numbers found for the customer");
            }
        } catch (SQLException e) {
            System.out.println("Database failed to fetch customer phone numbers");
        }
        return phoneNumbers;
    }
    public static boolean addPhoneNumber(Customer customer, String phoneNumber) {
        int customerId = customer.getUserID();
        final String sql = "INSERT INTO PhoneNumber (customer_id, phone_number) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.setString(2, phoneNumber);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Database failed to add phone number to customer");
            return false;
        }
    }
    public static ArrayList<Order> fetchCustomerOrders(Customer customer) {
        int customerId = customer.getUserID();
        ArrayList<Order> orders = new ArrayList<>();
        final String sql = "SELECT order_id, order_date, total_amount FROM Orders WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    Date orderDate = resultSet.getDate("order_date");
                    double totalAmount = resultSet.getDouble("total_amount");
                    orders.add(new Order(orderId, orderDate, totalAmount));
                }
            } catch (SQLException e) {
                System.out.println("No orders found for the customer");
            }
        } catch (SQLException e) {
            System.out.println("Database failed to fetch customer orders");
        }
        return orders;
    }
    public static boolean insertCustomerOrder(Customer customer, Order order) {
        int customerId = customer.getUserID();
        final String sql = "INSERT INTO Orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setDouble(3, order.getTotalAmount());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Database failed to add order to customer");
            return false;
        }
    }

}
