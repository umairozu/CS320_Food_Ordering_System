package FOS_CORE;

public class CartItem {

    private MenuItem item;
    private int quantity;
    private double price;

    public CartItem() { }

    public CartItem(MenuItem item, int quantity, double price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // UML: calculateItemTotal(item, quantity) : double
    public double calculateItemTotal() {
        return price * quantity;
    }
}
