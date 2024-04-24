package FOMS.order_manager;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import FOMS.process_manager.ProcessOrder;

import java.io.IOException;
import java.time.Duration;

    /**
     * The {@code Order} class represents an order within FOMS. It maintains the order details including items in the order, 
     * total cost, order status, and order type. This class also handles the timing for order readiness
     * and automatically cancels orders that are not collected within a certain timeframe.
     *
     * <p>Orders can have statuses such as "New", "Ready for Pickup", and "Completed". The class provides methods to add items to the
     * order, calculate the total cost, manage order status, and initiate a cancellation timer for uncollected orders.</p>
     *
     * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
     * @version 1.0
     * @since 2024-04-24
     */

public class Order {
    private List<OrderItem> orderItems;
    private String orderId;
    private double total;
    private String status; // Possible values: "New", "Ready to Pickup", "Completed"
    private String orderType;
    private LocalDateTime readyTime;
    private Timer cancellationTimer;

    /**
     * Constructs an order with specified details. Initializes the order with the given order ID, status, total cost, order type,
     * and list of order items.
     *
     * @param orderId The unique identifier for the order.
     * @param status The current status of the order.
     * @param total The total cost of the order.
     * @param orderType The type of the order (e.g., "takeaway", "delivery").
     * @param orderItems The list of items included in the order.
     */
    public Order(String orderId, String status, double total, String orderType, List<OrderItem> orderItems ) {
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.status = status; 
        this. orderType = orderType;
        this.orderItems = orderItems;
        this.total=total;
        setStatus(status);
    }

    /**
     * Constructs an order with a default status of "New". This constructor is typically used when an order is being
     * created from scratch.
     *
     * @param orderId The unique identifier for the order.
     */
    public Order(String orderId) {
        this.orderItems = new ArrayList<>();
        this.orderId = orderId;
        this.status = "New"; // Default status
    }

    /**
     * Adds an item to the order and recalculates the total cost.
     *
     * @param item The item to add to the order.
     */
    public void addItem(OrderItem item) {
        this.orderItems.add(item);
        calculateTotal();
    }

    /**
     * Calculates the total cost of the order based on the cost and quantity of each item.
     */
    private void calculateTotal() {
        this.total = 0.0;
        for (OrderItem item : orderItems) {
            this.total += item.getMenuItem().getCost() * item.getQuantity();
        }
    }

    /**
     * Retrieves the list of {@code OrderItem} objects contained within the order.
     * Each {@code OrderItem} represents a specific item in the order, including its quantity and any customizations.
     *
     * @return A list of {@code OrderItem} objects representing the items in the order.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Retrieves the unique identifier for the order. This ID is used to track and manage the order
     * throughout its lifecycle in the system.
     *
     * @return The string representing the unique order ID.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Calculates and returns the total cost of the order. This is computed by summing up the cost of all
     * items in the order, including their quantities.
     *
     * @return The total cost of the order as a double.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Retrieves the current status of the order. The status could be "New", "Ready for Pickup", "Completed",
     * or other states defined within the system to track the progress of the order.
     *
     * @return The current status of the order as a string.
     */
    public String getStatus() {
        return status;
    }

    
    /**
     * Sets the order type (e.g., "takeaway", "delivery"). This defines how the order should be processed
     * in terms of its fulfillment method.
     *
     * @param orderType The type of the order as a string.
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Retrieves the type of the order. The order type influences how the order is handled by the system,
     * affecting processes such as processing and collection.
     *
     * @return The type of the order as a string.
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the status of the order. If the status is set to "Ready for Pickup", the readiness time is also recorded.
     *
     * @param status The new status of the order.
     */
    public void setStatus(String status) {
        if ("Ready for Pickup".equals(status) && !"Ready for Pickup".equals(this.status)) {
            this.readyTime = LocalDateTime.now(); // Set the timestamp when order becomes ready
        }
        this.status = status;
    }

    /**
     * Returns the date and time when the order was marked as ready for pickup.
     * This is only set when the order status changes to "Ready for Pickup".
     *
     * @return The {@code LocalDateTime} when the order was ready, or null if not set.
     */
    public LocalDateTime getReadyTime() {
        return this.readyTime;
    }

    /**
     * Initiates a timer that will cancel the order if it is not collected within a specified timeout period.
     * This method is intended to be used when an order is marked as ready for pickup.
     *
     * @param ordersList The list containing all current orders, used for updating the status in the system.
     * @param filename The filename where the order statuses are persisted.
     */
    public void startCancellationTimer(List<Order> ordersList, String filename) {
        System.out.println("Starting cancellation timer for order " + orderId);
        cancellationTimer = new Timer();
        cancellationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer task running for order " + orderId);
                if ("Completed".equals(status)) {
                    cancelTimer();
                
                }else{
                    setStatus("Cancelled");
                    System.out.println("Order " + orderId + " has been cancelled due to not being picked up on time.");
    
                    // Now save the changes to file
        
                    ProcessOrder.saveOrderListToFile(ordersList, filename);
                }
            }
        }, 60 * 1000);  
    }
    
    
    /**
     * Cancels the active cancellation timer if the order has been collected. This is necessary to stop the timer
     * from executing if the order status changes to "Completed" before the timer elapses.
     */
    public void cancelTimer() {
        if (cancellationTimer != null) {
            cancellationTimer.cancel();
            System.out.println("TOrder has been collected! " + orderId);
        }
    }
}


