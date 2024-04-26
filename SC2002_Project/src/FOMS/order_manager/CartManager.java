package FOMS.order_manager;

import FOMS.menu_manager.MenuItem;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.text.DecimalFormat;
    /**
     * The {@code CartManager} class manages the shopping cart in FOMS. It allows items to be
     * added, removed, or updated within the cart with or without customizations. The class also provides functionality to display
     * cart items, calculate the total cost, and manage the quantity of items.
     *
     * <p>This class encapsulates all cart-related operations, ensuring that items are stored, modified, and retrieved in an
     * efficient and controlled manner. It uses a {@code LinkedHashMap} to maintain the order of insertion for items, which
     * aids in presenting items to the user in the order they were added.</p>
     *
     * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
     * @version 1.0
     * @since 2024-04-24
     */

public class CartManager {
    private Map<String, OrderItem> cartItems = new LinkedHashMap<>();

    /**
     * Adds an item to the cart or updates the quantity if the item already exists.
     * Items are uniquely identified by their name and any customizations specified.
     *
     * @param item The {@code MenuItem} to be added to the cart.
     * @param quantity The number of items to be added.
     * @param customization A string detailing any specific customization requests for the item.
     */
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


     /**
     * Removes a specific item from the cart based on item details and customization.
     *
     * @param item The {@code MenuItem} to remove from the cart.
     * @param customization A string representing the customization of the item to be removed.
     */
    public void removeItem(MenuItem item, String customization) {
        String key = item.getItem() + "-" + customization.toLowerCase();
        cartItems.remove(key);
    }
    

    /**
     * Clears all items from the cart, resetting it to an empty state.
     */
    public void clearCart() {
        cartItems.clear();
        System.out.println("Cart cleared.");
    }

    /**
     * Displays the items currently in the cart along with their quantities and any customizations.
     */
    public void displayItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (OrderItem orderItem : cartItems.values()) {
                String customizationDisplay = orderItem.getCustomization().isEmpty() ? "No customization" : orderItem.getCustomization();
                System.out.println(orderItem.getMenuItem().getItem() + " - Quantity: " + orderItem.getQuantity() + " - Customization: " + customizationDisplay);
            }
        }
    }
    
    /**
     * Calculates the total cost of the items in the cart.
     *
     * @return The total cost as a double, rounded to two decimal places.
     */

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : cartItems.values()) {
            total += item.getMenuItem().getCost() * item.getQuantity();
        }
        
        // Round the total to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        
        return total;
    }
    
    /**
     * Checks if the cart is empty.
     *
     * @return {@code true} if the cart is empty, {@code false} otherwise.
     */

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
    

    /**
     * Provides a copy of the items in the cart along with their quantities.
     *
     * @return A {@code Map} of {@code OrderItem} to {@code Integer} representing the items and their quantities.
     */
    public Map<OrderItem, Integer> getItems() {
        Map<OrderItem, Integer> itemsWithQuantities = new LinkedHashMap<>();
        for (OrderItem orderItem : cartItems.values()) {
            itemsWithQuantities.put(orderItem, orderItem.getQuantity());
        }
        return itemsWithQuantities;
    }



    /**
     * Updates the quantity of an existing item in the cart, removing it if the quantity is zero or less.
     *
     * @param item The {@code MenuItem} whose quantity is to be updated.
     * @param newQuantity The new quantity of the item. If zero, the item is removed from the cart.
     * @param customization The customization details of the item.
     */
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
    
    /**
     * Retrieves the customization details for a specific {@code MenuItem} from the cart. If the item is found in the cart,
     * this method returns the customization string associated with it. If the item is not in the cart or has no customizations,
     * it returns an empty string.
     *     
     * @param item The {@code MenuItem} for which customization details are sought.
     * @return A string containing the customization details if present; otherwise, an empty string.
     */

    public String getCustomizations(MenuItem item) {
        for (OrderItem orderItem : cartItems.values()) {
            if (orderItem.getMenuItem().equals(item)) {
                return orderItem.getCustomization();
            }
        }
        return ""; // Return empty string if no customizations are found.
    }    
}
