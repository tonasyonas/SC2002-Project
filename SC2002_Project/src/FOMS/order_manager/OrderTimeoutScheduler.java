package FOMS.order_manager;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OrderTimeoutScheduler {
    private static final long CHECK_INTERVAL = 1; // Check every 1 minute
    private static final long PICKUP_TIMEOUT = 5; // 5 minutes timeout

    public static void startScheduler(List<Order> orders) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            orders.forEach(order -> {
                if (order.getStatus().equals("Ready to Pickup") && order.getReadyTime() != null) {
                    long minutesSinceReady = java.time.Duration.between(order.getReadyTime(), now).toMinutes();
                    if (minutesSinceReady >= PICKUP_TIMEOUT) {
                        order.setStatus("Cancelled");
                        System.out.println("Order " + order.getOrderId() + " cancelled due to non-pickup.");
                    }
                }
            });
        }, 0, CHECK_INTERVAL, TimeUnit.MINUTES);
    }
}
