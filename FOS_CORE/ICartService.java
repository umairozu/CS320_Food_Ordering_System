package FOS_CORE;

public interface ICartService {
    void addToCart(Customer customer, MenuItem item, int quantity, double price);
    boolean removeFromCart(Customer customer, MenuItem item);
    void updateCartItemQuantity(Customer customer, MenuItem item, int newQuantity);
}