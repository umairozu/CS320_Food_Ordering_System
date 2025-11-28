package FOS_CORE;

public class CartItem {

    private MenuItem item;
    private int quantity;

    public CartItem() { }

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // UML: calculateItemTotal(item, quantity) : double
    public double calculateItemTotal() {
        return item.getPrice() * quantity;
    }
}
