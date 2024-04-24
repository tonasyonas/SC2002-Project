package FOMS.process_manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import FOMS.order_manager.Order;
import FOMS.order_manager.OrderItem;
import java.util.Timer;
import java.util.TimerTask;

public class ProcessOrder {

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


