package FOS_CORE;

import java.util.List;

public interface IAccountService {
    Customer createCustomerAccount(String email, String phone, String password);
    void changePassword(User user, String newPassword);
    void updateContactInfo(Customer customer, String phone);
    boolean addAddress(Customer customer, Address address);
    List<Address> getAddresses(Customer customer);
}