package FOS_CORE;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private OrderStatus status;
    private Date timestamp;
    private double totalAmount;
    private int rating;
    private String reviewComment;

    // The following attributes were not implemented in the figiure
    private Customer customer;
    private Address deliveryAddress;
    private List<CartItem> items = new ArrayList<>();

    public Order() { } // this will be used for the DAO

    public Order(int orderID, Customer customer, Address deliveryAddress) {
        this.orderID = orderID;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.status = OrderStatus.PENDING;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }
    public Integer getRating() {
        return rating;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public double calculateOrderTotal() {
        // TODO calculate the total value for the order.
        return 0;
    }

    public void rateOrder(int rating, String comment) {
        this.rating = rating;
        this.reviewComment = comment;
    }

    public boolean validateOrder() {
        return customer != null && deliveryAddress != null && !items.isEmpty();
    }
}
