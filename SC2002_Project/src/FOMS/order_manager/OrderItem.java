package FOMS.order_manager;
import FOMS.menu_manager.*;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String customization;
    
    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customization = customization;
    }
    
    // Constructor
    public OrderItem(MenuItem menuItem, int quantity, String customization){
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customization = customization;
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

    // Getter and setter for customization
    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }


}
