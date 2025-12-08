package FOS_CORE;

import java.sql.*;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private OrderStatus status;
    private Timestamp creationDate;
    private Rating rating;
    private String deliveryAddress;
    private ArrayList<CartItem> items;
    private String phoneNumber;
    private String CardNumber;
    private String restaurantName;


    public Order(String deliveryAddress, ArrayList<CartItem> items, String restaurantName, String phoneNumber, String cardNumber) {
        this.deliveryAddress = deliveryAddress;
        this.creationDate = new Timestamp(System.currentTimeMillis());
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.restaurantName = restaurantName;
        this.phoneNumber = phoneNumber;
        this.CardNumber = cardNumber;
    }
    public Order(String deliveryAddress, ArrayList<CartItem> items, Timestamp date, OrderStatus status, String restaurantName, int orderID, Rating rating, String phoneNumber, String cardNumber) {
        this.deliveryAddress = deliveryAddress;
        this.creationDate = date;
        this.items = items;
        this.status = status;
        this.restaurantName = restaurantName;
        this.orderID = orderID;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.CardNumber = cardNumber;
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public Rating getRating() {
        return rating;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setDate(Timestamp date) {
        this.creationDate = date;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
