package FOS_CORE;

import java.util.List;

public class AccountService implements IAccountService {

    private final DatabaseAPI DB = new DatabaseAPI();
    static int userID = 0;
    // Working on it : Mohamed Khaled Becetti  )
    @Override
    public Customer createCustomerAccount(String email, String phone, String password) {
        User user = new Customer(userID, email, password);
        DB.addNewCustomer(user);
        DB.addPhoneNumber((Customer)user,phone);
        return null;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        // TODO: Implementation
    }

    @Override
    public void updateContactInfo(Customer customer, String phone) {
        // TODO: Implementation
    }

    @Override
    public boolean addAddress(Customer customer, Address address) {
        // TODO: Implementation
        return true;
    }

    @Override
    public List<Address> getAddresses(Customer customer) {
        // TODO: Implementation
        return null;
    }

    private boolean validateEmailFormat(String email) {
        // TODO: Implementation
        return false;
    }

    private void saveCustomer(Customer customer) {
        // TODO: Implementation
    }
}