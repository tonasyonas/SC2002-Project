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
    private static final long CHECK_INTERVAL = 1; // Check every 1 minute
    private static final long PICKUP_TIMEOUT = 5; // 5 minutes timeout
    private static final String filename = "SC2002_Project/src/FOMS/order_manager/order.txt";
    private static ScheduledExecutorService scheduler;  // Make scheduler a static field

    public static void startScheduler(List<Order> orders) {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();  // Initialize scheduler if null or previously shutdown
        }

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

    public static void stopScheduler() {
        if (scheduler != null) {
            scheduler.shutdown();
            try {
                // Wait a while for existing tasks to terminate
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Scheduler did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                scheduler.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
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
