package FOMS.process_manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import FOMS.order_manager.Order;

public class OrderTimeoutScheduler {
    private static final long CHECK_INTERVAL = 1; // Chstaeck every 1 minute
    private static final long PICKUP_TIMEOUT = 5; // 5 minutes timeout
    private static final String filename = "SC2002_Project/src/FOMS/order_manager/order.txt";

    public static void startScheduler(List<Order> orders) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            boolean needsSave = false;
            for (Order order : orders) {
                if ("Ready to Pickup".equals(order.getStatus()) && order.getReadyTime() != null) {
                    long minutesSinceReady = java.time.Duration.between(order.getReadyTime(), now).toMinutes();
                    if (minutesSinceReady >= PICKUP_TIMEOUT) {
                        order.setStatus("Cancelled");
                        System.out.println("Order " + order.getOrderId() + " cancelled due to non-pickup.");
                        needsSave = true;
                    }
                }
            }
            if (needsSave) {
                try {
                    saveOrderListToFile(orders, filename);
                } catch (IOException e) {
                    System.err.println("Failed to save order list to file: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, 0, CHECK_INTERVAL, TimeUnit.MINUTES);
    }

    private static void saveOrderListToFile(List<Order> orders, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : orders) {
                String orderDetails = ProcessOrder.formatOrderDetails(order);
                writer.write(orderDetails);
                writer.newLine(); // To ensure each order is on a new line.
            }
            System.out.println("Order list saved successfully to " + filename);
        }
    }
}
