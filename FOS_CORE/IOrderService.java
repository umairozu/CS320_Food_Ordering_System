package FOS_CORE;
import java.util.ArrayList;

public interface IOrderService {
    void placeOrder(Customer customer,Address address, Order order, Restaurant restaurant);
    Order trackOrder(String orderID);
    ArrayList<Order> getOrderHistory(Customer customer);
    void rateOrder(Order order, int rating, String comment);
}