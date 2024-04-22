package FOMS.order_manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import FOMS.menu_manager.*;

public class OrderManager {
    private Map<String, Order> orders = new HashMap<>();
    private static int nextOrderId = 1; // Static variable to keep track of the next order ID
    private static final String ORDER_ID_FILE = "SC2002_Project/src/FOMS/order_manager/orderid.txt";
    private Scanner scanner = new Scanner(System.in);


    public OrderManager() {
        orders = new HashMap<>();
        loadNextOrderId();
    }

    public String placeOrder(CartManager cartManager, String orderType) {
        if (cartManager.isEmpty()) {
            System.out.println("Cannot place an empty order.");
            return null;
        }
        String orderId = String.valueOf(nextOrderId++); // Use and increment the static order ID counter
        Order newOrder = new Order(orderId);
        Map<MenuItem, Integer> cartItems = cartManager.getItems();
        cartItems.forEach((item, quantity) -> {
            OrderItem orderItem = new OrderItem(item, quantity);
            newOrder.addItem(orderItem);
        });
        newOrder.setStatus(OrderStatus.NEW.toString());
        orders.put(orderId, newOrder);
        cartManager.clearCart();
        saveNextOrderId(); // Save the next order ID for persistence
        return orderId;
    }
    
    public OrderStatus getOrderStatus(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            return OrderStatus.valueOf(order.getStatus().toUpperCase());
        }
        return null; // Or throw an exception or similar error handling
    }


    private void saveNextOrderId() {
        try (PrintWriter out = new PrintWriter(new FileWriter(ORDER_ID_FILE))) {
            out.println(nextOrderId);
        } catch (IOException e) {
            System.err.println("Failed to save next order ID: " + e.getMessage());
        }
    }
    
    private void loadNextOrderId() {
        try (Scanner in = new Scanner(new FileReader(ORDER_ID_FILE))) {
            if (in.hasNextInt()) {
                nextOrderId = in.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Order ID file not found. Starting with order ID 1.");
        } catch (IOException e) {
            System.err.println("Failed to load next order ID: " + e.getMessage());
        }
    }
    
}
