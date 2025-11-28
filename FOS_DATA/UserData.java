package FOS_DATA;

import FOS_CORE.Customer;
import FOS_CORE.Manager;
import FOS_CORE.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public abstract class UserData {
    abstract boolean addNewCustomer(User user);
    abstract User getUserByEmail(String email);
    abstract boolean changeUserPassword(User user, String newHashedPassword);
    abstract ArrayList<Card> fetchCustomerCards(Customer customer);
    abstract boolean addCardToCustomer(Customer customer, Card card);
    abstract ArrayList<Address> fetchCustomerAddresses(Customer customer);
    abstract boolean addAddress(Customer customer,Address address);
    abstract ArrayList<String> fetchCustomerPhoneNumbers(Customer customer);
    abstract boolean addPhoneNumber(Customer customer, String phoneNumber);
    abstract ArrayList<Order> fetchCustomerOrders(Customer customer);
    abstract boolean insertCustomerOrder(Customer customer, Order order);
}
