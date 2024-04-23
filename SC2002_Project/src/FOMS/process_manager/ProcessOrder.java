package FOMS.process_manager;

import FOMS.order_manager.Order;
import FOMS.order_manager.OrderItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.*;

public class ProcessOrder {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10); // Configure pool size as needed

    public static List<Order> processOrderID(String orderIdToFind, String status, String filename) {
        try {
            List<Order> ordersList = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : ordersList) {
                if (order.getOrderId().equals(orderIdToFind)) {
                    order.setStatus(status);
                    if ("Ready for Pickup".equals(status)) {
                        scheduleOrderTimeout(order, 5, ordersList, filename); // Schedule to mark as 'Cancelled' after 5 minutes
                    }
                    saveOrderListToFile(ordersList, filename); // Save the updated order list to file
                    return ordersList;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void scheduleOrderTimeout(Order order, long delayMinutes, List<Order> ordersList, String filename) {
        ScheduledFuture<?> timeoutHandle = scheduler.schedule(() -> {
            order.setStatus("Cancelled");
            System.out.println("Order " + order.getOrderId() + " cancelled due to non-pickup.");
            try {
                saveOrderListToFile(ordersList, filename); // Save changes to file
            } catch (IOException e) {
                System.err.println("Failed to save order update to file: " + e.getMessage());
            }
        }, delayMinutes, TimeUnit.MINUTES);

        

    private static void saveOrderListToFile(List<Order> ordersList, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : ordersList) {
                String orderDetails = formatOrderDetails(order);
                writer.write(orderDetails);
                writer.newLine();
            }
            System.out.println("Order list saved successfully to " + filename);
        }
    }

    private static String formatOrderDetails(Order order) {
        DecimalFormat df = new DecimalFormat("#0.00");

        StringBuilder sb = new StringBuilder();
        sb.append(order.getOrderId()).append(';')
          .append(order.getStatus()).append(';')
          .append(df.format(order.getTotal())).append(';')
          .append(order.getOrderType()).append(';');

        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            sb.append(order.getOrderItems().get(0).getMenuItem().getBranch()).append(';');
            for (OrderItem item : order.getOrderItems()) {
                sb.append(item.getMenuItem().getItem()).append(", ")
                  .append(item.getQuantity()).append(", ")
                  .append(df.format(item.getMenuItem().getCost()));
                if (item.getCustomization() != null && !item.getCustomization().isEmpty()) {
                    sb.append(", ").append(item.getCustomization());
                }
                sb.append("; ");
            }
            // Remove the last semicolon and space
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
