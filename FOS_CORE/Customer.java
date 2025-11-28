package FOS_CORE;

import FOS_DATA.UserData;
import FOS_DATA.UserDataAccess;

import java.util.ArrayList;
import FOS_DATA.UserDataAccess.*;

public class Customer extends User {

    private ArrayList<String> phoneNumbers;
    private ArrayList<Address> addresses;
    private ArrayList<Order> orders;
    private ArrayList<Card> cards;

    public Customer() { }

    public Customer(int userID, String email, String passwordHash) {
        super(userID, email, passwordHash);
        this.phoneNumbers = UserDataAccess.fetchCustomerPhoneNumbers(this);
        this.addresses = UserDataAccess.fetchCustomerAddresses(this);
        this.orders = UserDataAccess.fetchCustomerOrders(this);
        this.cards = UserDataAccess.fetchCustomerCards(this);
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }
}
