package FOMS.order_manager;

import FOMS.menu_manager.MenuItem;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class CartManager {
    private Map<String, OrderItem> cartItems = new LinkedHashMap<>();

    // Method to add an item to the cart with possible customizations
    public void addItem(MenuItem item, int quantity, String customization) {
        // Create a unique key based on item name and customization
        String key = item.getItem() + "-" + customization.toLowerCase();

        if (cartItems.containsKey(key)) {
            OrderItem existingItem = cartItems.get(key);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cartItems.put(key, new OrderItem(item, quantity, customization));
        }
    }


    public void removeItem(MenuItem item, String customization) {
        String key = item.getItem() + "-" + customization.toLowerCase();
        cartItems.remove(key);
    }
    

    public void clearCart() {
        cartItems.clear();
        System.out.println("Cart cleared.");
    }

    public void displayItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            int itemNumber = 1;
            for (OrderItem item : cartItems.values()) {
                System.out.println(itemNumber++ + ". " + item.getMenuItem().getItem() + 
                                   " - Quantity: " + item.getQuantity() + 
                                   " - Customization: " + item.getCustomization());
            }
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : cartItems.values()) {
            total += item.getMenuItem().getCost() * item.getQuantity();
        }
        return total;
    }
    

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
    

    // Method to get a copy of the items in the cart
    public Map<OrderItem, Integer> getItems() {
        Map<OrderItem, Integer> itemsWithQuantities = new LinkedHashMap<>();
        for (OrderItem orderItem : cartItems.values()) {
            itemsWithQuantities.put(orderItem, orderItem.getQuantity());
        }
        return itemsWithQuantities;
    }



    // Method to update the quantity of an item, identified by MenuItem and customization
    public void updateItemQuantity(MenuItem item, int newQuantity, String customization) {
        String key = item.getItem() + "-" + customization.toLowerCase();
        OrderItem orderItem = cartItems.get(key);

        if (orderItem != null && newQuantity > 0) {
            orderItem.setQuantity(newQuantity);
            // Assuming setCustomization is a method within OrderItem that updates customization
            orderItem.setCustomization(customization);
            System.out.println("Item quantity and customization updated.");
        } else if (orderItem != null && newQuantity <= 0) {
            cartItems.remove(key);
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in the cart.");
        }
    }
    

    public String getCustomizations(MenuItem item) {
        for (OrderItem orderItem : cartItems.values()) {
            if (orderItem.getMenuItem().equals(item)) {
                return orderItem.getCustomization();
            }
        }
        return ""; // Return empty string if no customizations are found.
    }    
}
