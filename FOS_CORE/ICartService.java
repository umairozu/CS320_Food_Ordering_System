package FOS_CORE;

public interface ICartService {
    void addToCart(Customer customer, MenuItem item, int quantity);
    void updateCartItem(Customer customer, MenuItem item, int newQuantity);
}