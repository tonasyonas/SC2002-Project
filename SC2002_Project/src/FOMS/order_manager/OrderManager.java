package FOMS.order_manager;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

import FOMS.menu_manager.*;
import FOMS.process_manager.*;

/**
 * Manages orders placed by customers, including creation, status tracking, and persistence.
 * The OrderManager class interacts with the CartManager to process and place orders.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class OrderManager {
    private Map<String, Order> orders = new HashMap<>();
    private static int nextOrderId = 1; // Static variable to keep track of the next order ID
    private static final String ORDER_ID_FILE = "SC2002_Project/src/FOMS/order_manager/orderid.txt";
    private static final String ORDER_FILE = "SC2002_Project/src/FOMS/order_manager/order.txt";
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an OrderManager object.
     * Initializes the map to store orders and loads next order ID from file.
     */
    public OrderManager() {
        orders = new HashMap<>();
        loadNextOrderId();
        loadOrdersFromFile(); 
    }

    /**
     * Places an order based on the items in the provided CartManager.
     * Generates a new order ID, adds the order to the map, and clears the cart.
     * Saves the next order ID for persistence.
     *
     * @param cartManager The CartManager containing the items to be ordered.
     * @param orderType   The type of the order (e.g., dine-in, takeout).
     * @return The ID of the placed order, or null if the cart is empty.
     */
    public String placeOrder(CartManager cartManager, String orderType) {
        if (cartManager.isEmpty()) {
            System.out.println("Cannot place an empty order.");
            return null;
        }

        String orderId = String.valueOf(nextOrderId++);
        Order newOrder = new Order(orderId);
        newOrder.setOrderType(orderType); // Set the order type


        // Adjusted to work with the new Map structure in CartManager
        for (OrderItem orderItem : cartManager.getItems().keySet()) {
            // You can directly add the OrderItem as it now includes both the MenuItem and customization
            newOrder.addItem(new OrderItem(orderItem.getMenuItem(), orderItem.getQuantity(), orderItem.getCustomization()));
        }
        newOrder.setStatus(OrderStatus.NEW.toString());
        orders.put(orderId, newOrder);
        cartManager.clearCart();
        saveNextOrderId(); // Save the next order ID for persistence

        return orderId;
    }
    
    /**
     * Retrieves the status of a specific order identified by its order ID.
     *
     * @param orderId The ID of the order to check.
     * @return The status of the order, or null if the order ID is invalid.
     */
    public OrderStatus getOrderStatus(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            return OrderStatus.valueOf(order.getStatus().toUpperCase());
        }
        return null; // Or throw an exception or similar error handling
    }


    /**
     * Saves the next order ID to a file for persistence.
     */
    private void saveNextOrderId() {
        try (PrintWriter out = new PrintWriter(new FileWriter(ORDER_ID_FILE))) {
            out.println(nextOrderId);
        } catch (IOException e) {
            System.err.println("Failed to save next order ID: " + e.getMessage());
        }
    }
    
    /**
     * Loads the next order ID from a file for persistence.
     * If the file is not found, starts with order ID 1.
     */
    private void loadNextOrderId() {
        try (Scanner in = new Scanner(new FileReader(ORDER_ID_FILE))) {
            if (in.hasNextInt()) {
                nextOrderId = in.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Order ID file not found. Starting with order ID 1.");
        // } catch (IOException e) {
        //     System.err.println("Failed to load next order ID: " + e.getMessage());
         }
    }

    /**
     * Saves the details of the provided order to a file for persistence.
     *
     * @param order          The order to save.
     * @param selectedBranch The branch where the order was placed.
     * @param filename       The name of the file to save to.
     */
    public void saveOrderToFile(Order order, String SelectedBranch, String filename) {
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

    /**
     * Loads orders from a file for persistence and adds them to the orders map.
     */
    private void loadOrdersFromFile() {
        // Assuming you have a method to read orders from file
        try {
            List<Order> loadedOrders = ReadOrderList.readOrdersFromFile(ORDER_FILE);
            for (Order order : loadedOrders) {
                orders.put(order.getOrderId(), order);
            }
            System.out.println("Loaded " + loadedOrders.size() + " orders from file.");
        } catch (IOException e) {
            System.err.println("Failed to load orders from file: " + e.getMessage());
        }
    }

    /**
     * Formats the details of the provided order for storage in a file.
     *
     * @param order The order to format.
     * @return A formatted string containing order details.
     */
    private String formatOrderDetails(Order order) {
    DecimalFormat df = new DecimalFormat("#0.00");

    StringBuilder sb = new StringBuilder();
    sb.append(order.getOrderId()).append(';')
    .append(order.getStatus()).append(';')
    .append(df.format(order.getTotal())).append(';') // Format total to two decimal places
    .append(order.getOrderType()).append(';')
    .append(order.getOrderItems().get(0).getMenuItem().getBranch()).append(';');

    for (OrderItem item : order.getOrderItems()) {
        String food = item.getMenuItem().getItem();
        int quantity = item.getQuantity();
        double price = item.getMenuItem().getCost();
        String customizations = item.getCustomization();

        // Append food, quantity, price, and customizations
        sb.append(food).append(", ").append(quantity).append(", ").append(df.format(price)).append(", ");
        if (!customizations.isEmpty()) {
            sb.append(customizations);
        }
        sb.append("; ");
    }


    // Remove the last semicolon and space from the item list
    if (!order.getOrderItems().isEmpty()) {
        sb.setLength(sb.length() - 2);
    }

    return sb.toString();
}

    /**
     * Retrieves the order associated with the provided order ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The order corresponding to the provided ID, or null if not found.
     */
    public Order getOrderById(String orderId) {
        // Logic to retrieve the Order object from wherever it's stored, e.g., a map or database
        return orders.get(orderId);
    }

}
