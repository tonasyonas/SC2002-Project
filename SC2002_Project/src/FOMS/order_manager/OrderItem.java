package FOMS.order_manager;
import FOMS.menu_manager.*;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    
    // Constructor
    public OrderItem(MenuItem menuItem, int quantity){
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // getter for menuitem
    public MenuItem getMenuItem() {
        return menuItem;
    }

    // setter for menuitem
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
