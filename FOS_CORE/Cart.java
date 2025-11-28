package FOS_CORE;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int cartID;
    private List<CartItem> items = new ArrayList<>();

    public Cart() { }

    public Cart(int cartID) {
        this.cartID = cartID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    // UML: calculateTotal() : double
    public double calculateTotal() {
        // TODO calculate the total value of the cart from the items in the arraylist.
        return 0;
    }
}
