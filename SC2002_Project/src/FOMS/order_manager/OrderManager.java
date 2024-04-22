package FOMS.order_manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import FOMS.menu_manager.*;

public class OrderManager {
    private Map<String, Order> orders = new HashMap<>();
    private static int nextOrderId = 1; // Static variable to keep track of the next order ID
    private static final String ORDER_ID_FILE = "SC2002_Project/src/FOMS/order_manager/orderid.txt";
    private static final String ORDER_FILE = "SC2002_Project/src/FOMS/order_manager/order.txt";
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
        String orderId = String.valueOf(nextOrderId++);
        Order newOrder = new Order(orderId);
        Map<MenuItem, Integer> cartItems = cartManager.getItems();
        cartItems.forEach((item, quantity) -> {
            // Here, we should also get the customizations for each item
            String customization = cartManager.getCustomizations(item); // Make sure this method exists in your CartManager and returns a non-null string
            OrderItem orderItem = new OrderItem(item, quantity, customization);
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

    public void saveOrderToFile(Order order, String filename) {
        // Format the order details
        String orderDetails = formatOrderDetails(order);

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(orderDetails);
            writer.newLine(); // To ensure each order is on a new line.
            System.out.println("Order saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save order to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String formatOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(order.getOrderId()).append(';')
          .append(order.getStatus()).append(';')
          .append(order.getTotal()).append(';');

        for (OrderItem item : order.getOrderItems()) {
            sb.append(item.getMenuItem().getItem()).append('x')
              .append(item.getQuantity()).append('@')
              .append(item.getMenuItem().getCost()).append(',');

              if (!item.getCustomization().isEmpty()) {
                sb.append('(').append(item.getCustomization()).append(')');
            }
            
            sb.append(',');
        }

        

        // Remove the last comma from the item list
        if (!order.getOrderItems().isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
    
    public Order getOrderById(String orderId) {
        // Logic to retrieve the Order object from wherever it's stored, e.g., a map or database
        return orders.get(orderId);
    }
    


}
