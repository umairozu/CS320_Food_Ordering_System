package FOS_CORE;

import java.sql.*;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private OrderStatus status;
    private Date creationDate;
    private Rating rating;
    private Address deliveryAddress;
    private ArrayList<CartItem> items;

    public Order() { } // this will be used for the DAO

    public Order(int orderID, Address deliveryAddress, ArrayList<CartItem> items) {
        this.orderID = orderID;
        this.deliveryAddress = deliveryAddress;
        this.creationDate = new Date(System.currentTimeMillis());
        this.items = items;
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

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Rating getRating() {
        return rating;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setDate(Date date) {
        this.creationDate = date;
    }
}
