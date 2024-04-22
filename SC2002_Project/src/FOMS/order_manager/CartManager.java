package FOMS.order_manager;

import FOMS.menu_manager.MenuItem;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CartManager {
    private List<OrderItem> items = new ArrayList<>();

    // Method to add an item to the cart with possible customizations
    public void addItem(MenuItem item, int quantity, String customization) {
        for (OrderItem orderItem : items) {
            if (orderItem.getMenuItem().equals(item) && orderItem.getCustomization().equals(customization)) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(new OrderItem(item, quantity, customization));
    }


    public void removeItem(MenuItem item) {
        items.removeIf(orderItem -> orderItem.getMenuItem().equals(item));
    }

    public void clearCart() {
        items.clear();
        System.out.println("Cart cleared.");
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (OrderItem item : items) {
                System.out.println(item.getMenuItem().getItem() + " x " + item.getQuantity() + " Customization: " + item.getCustomization()); // Include customization details
            }
        }
    }
    

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getMenuItem().getCost() * item.getQuantity();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Method to get a copy of the items in the cart
    public Map<MenuItem, Integer> getItems() {
        Map<MenuItem, Integer> cartItems = new HashMap<>();
        for (OrderItem orderItem : items) {
            cartItems.put(orderItem.getMenuItem(), orderItem.getQuantity());
        }
        return cartItems;
    }


    // Method to update the quantity of an item, identified by MenuItem and customization
    public void updateItemQuantity(MenuItem item, int newQuantity, String newCustomization) {
        // Find the order item that matches the menu item, regardless of its current customization
        OrderItem orderItemToUpdate = null;
        for (OrderItem orderItem : items) {
            if (orderItem.getMenuItem().equals(item)) {
                orderItemToUpdate = orderItem;
                break;
            }
        }
    
        if (orderItemToUpdate != null) {
            // If we found an item and the new quantity is not zero, update quantity and customization
            if (newQuantity > 0) {
                orderItemToUpdate.setQuantity(newQuantity);
                orderItemToUpdate.setCustomization(newCustomization);
                System.out.println("Item quantity and customization updated.");
            } else {
                // If the quantity is 0, remove the item
                items.remove(orderItemToUpdate);
                System.out.println("Item removed from cart.");
            }
        } else {
            // If no matching item was found
            System.out.println("Item not found in the cart.");
        }
    }
    

    public String getCustomizations(MenuItem item) {
        return item.getCustomizations(); // Call getCustomizations() method of MenuItem
    }


}
