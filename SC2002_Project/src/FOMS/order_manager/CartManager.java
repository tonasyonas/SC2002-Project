package FOMS.order_manager;

import java.util.Map;
import java.util.HashMap;
import FOMS.menu_manager.*;


public class CartManager {
    private Map<MenuItem, Integer> items = new HashMap<>();

    public void addItem(MenuItem item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public void removeItem(MenuItem item, int quantity) {
        items.computeIfPresent(item, (k, v) -> v - quantity > 0 ? v - quantity : null);
    }

    public void clearCart() {
        items.clear();
        System.out.println("Cart cleared.");
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            items.forEach((item, quantity) -> System.out.println(item.getItem() + " x " + quantity));
        }
    }

    public double calculateTotal() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getCost() * e.getValue())
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Method to get a copy of the items in the cart
    public Map<MenuItem, Integer> getItems() {
        return new HashMap<>(items); // Return a copy to protect the encapsulation
    }

    public void updateItemQuantity(MenuItem item, int newQuantity) {
        if (newQuantity > 0) {
            items.put(item, newQuantity);
            System.out.println("Item quantity updated.");
        } else if (newQuantity == 0) {
            items.remove(item);
            System.out.println("Item removed from cart.");
        }
    }

}

