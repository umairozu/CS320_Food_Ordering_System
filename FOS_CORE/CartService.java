package FOS_CORE;

import java.util.ArrayList;

public class CartService implements ICartService {

    @Override
    public void addToCart(Customer customer, MenuItem item, int quantity, double price) {
        CartItem cartItem = new CartItem(item, quantity, price);

    }

    @Override
    public void updateCartItem(Customer cart, MenuItem item, int newQuantity) {
        // TODO: Implemntation
    }


    private double calculateItemTotal(MenuItem item, int quantity) {
        // TODO: Implementation
        return 0.0;
    }

    private void validateCart(ArrayList<MenuItem> cart) {
        // TODO: Implementation
    }
}