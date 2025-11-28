package FOS_CORE;

public class Customer extends User {

    private String phoneNumber;

    public Customer() { }

    public Customer(String userID, String email, String passwordHash, String phoneNumber) {
        super(userID, email, passwordHash);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
