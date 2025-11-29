package FOS_CORE;
// working on it : Mohamed Khaled Becetti
public class CartService implements ICartService {

    @Override
    public void addToCart(Customer customer, MenuItem item, int quantity) {
        // TODO: Implementation
    }

    @Override
    public void updateCartItem(Customer cart, MenuItem item, int newQuantity) {
        // TODO: Implementation
    }


    private double calculateItemTotal(MenuItem item, int quantity) {
        // TODO: Implementation
        return 0.0;
    }

    private void validateCart(Cart cart) {
        // TODO: Implementation
    }
}