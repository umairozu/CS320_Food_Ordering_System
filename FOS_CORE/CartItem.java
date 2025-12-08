package FOS_CORE;

public class CartItem {

    private MenuItem menuItem;
    private int quantity;
    private double price;

    public CartItem() { }

    public CartItem(MenuItem menuItem, int quantity, double price) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem item) {
        this.menuItem = item;
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
