package FOMS.process_manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import FOMS.order_manager.Order;
import FOMS.order_manager.OrderItem;

/**
 * The {@code ProcessOrder} class provides functionality to process orders based on order ID.
 * It allows updating the status of an order and persisting those changes to a file.
 * This class is also responsible for starting and canceling a cancellation timer based on
 * order status changes.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24 
 */


public class ProcessOrder {

    /**
     * Processes an order by ID, updates its status, and optionally manages a cancellation timer.
     * It then saves the updated order list to a file.
     * 
     * @param orderIdToFind The ID of the order to process.
     * @param status The new status to set for the order.
     * @return A list of {@code Order} objects reflecting the updated state, or {@code null} if no matching order is found.
     * @throws IOException if there is an issue reading or writing to the file.
     */

    public static List<Order> processOrderID(String orderIdToFind, String status) {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt";
        try {
            List<Order> ordersList = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : ordersList) {
                if (order.getOrderId().equals(orderIdToFind)) {
                    order.setStatus(status);
                    if ("Ready for Pickup".equals(status)) {
                        order.startCancellationTimer(ordersList, filename);  // Start the timer when the order is ready
                    } else {
                        order.cancelTimer();  // Cancel the timer if the order status changes
                    }
                    saveOrderListToFile(ordersList, filename); // Save the updated order list to file
                    return ordersList; // Found the order with the specified ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return null; // Order with the specified ID not found
    }

    /**
     * Saves the list of orders to the specified file.
     * 
     * @param ordersList A list of {@code Order} objects to save.
     * @param filename The path of the file where the orders are to be saved.
     * @throws IOException if there is an issue writing to the file.
     */
    public static void saveOrderListToFile(List<Order> ordersList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : ordersList) {
                String orderDetails = formatOrderDetails(order);
                writer.write(orderDetails);
                writer.newLine(); // To ensure each order is on a new line.
            }
            System.out.println("Order list saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save order list to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Formats the details of an order into a string suitable for file storage.
     * It includes order ID, status, total, order type, branch, and item details.
     * 
     * @param order The {@code Order} object whose details are to be formatted.
     * @return A formatted string representation of the order details.
     */

    private static String formatOrderDetails(Order order) {
        DecimalFormat df = new DecimalFormat("#0.00");

        StringBuilder sb = new StringBuilder();
        sb.append(order.getOrderId()).append(';')
          .append(order.getStatus()).append(';')
          .append(df.format(order.getTotal())).append(';')
          .append(order.getOrderType()).append(';')
          .append(order.getOrderItems().get(0).getMenuItem().getBranch()).append(';');

        for (OrderItem item : order.getOrderItems()) {
            String food = item.getMenuItem().getItem();
            int quantity = item.getQuantity();
            double price = item.getMenuItem().getCost();
            String customizations = item.getCustomization();

            // Append food, quantity, price, and customizations
            sb.append(food).append(", ").append(quantity).append(", ").append(df.format(price)).append(", ");
            if (customizations != null && !customizations.isEmpty()) { // Check if customizations is not null
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
}


