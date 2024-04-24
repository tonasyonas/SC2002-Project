package FOMS.order_manager;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import FOMS.process_manager.ProcessOrder;

import java.io.IOException;
import java.time.Duration;


public class Order {
    private List<OrderItem> orderItems;
    private String orderId;
    private double total;
    private String status; // Possible values: "New", "Ready to Pickup", "Completed"
    private String orderType;
    private LocalDateTime readyTime;
    private Timer cancellationTimer;

    // Constructor
    public Order(String orderId, String status, double total, String orderType, List<OrderItem> orderItems ) {
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.status = status; 
        this. orderType = orderType;
        this.orderItems = orderItems;
        this.total=total;
        setStatus(status);
    }

    public Order(String orderId) {
        this.orderItems = new ArrayList<>();
        this.orderId = orderId;
        this.status = "New"; // Default status
    }

    // Method to add an item to the order
    public void addItem(OrderItem item) {
        this.orderItems.add(item);
        calculateTotal();
    }

    // Method to calculate the total cost of the order
    private void calculateTotal() {
        this.total = 0.0;
        for (OrderItem item : orderItems) {
            this.total += item.getMenuItem().getCost() * item.getQuantity();
        }
    }

    // Getter for orderItems
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    // Getter for total
    public double getTotal() {
        return total;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    // Method to get the order type
    public String getOrderType() {
        return orderType;
    }

    public void setStatus(String status) {
        if ("Ready for Pickup".equals(status) && !"Ready for Pickup".equals(this.status)) {
            this.readyTime = LocalDateTime.now(); // Set the timestamp when order becomes ready
        }
        this.status = status;
    }

    public LocalDateTime getReadyTime() {
        return this.readyTime;
    }
    public void startCancellationTimer(List<Order> ordersList, String filename) {
        System.out.println("Starting cancellation timer for order " + orderId);
        cancellationTimer = new Timer();
        cancellationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer task running for order " + orderId);
                if ("Ready for Pickup".equals(status)) {
                    setStatus("Cancelled");
                    System.out.println("Order " + orderId + " has been cancelled due to not being picked up on time.");
    
                    // Now save the changes to file
        
                    ProcessOrder.saveOrderListToFile(ordersList, filename);
                
                }
            }
        }, 5 * 60 * 1000);  
    }
    
    

    public void cancelTimer() {
        if (cancellationTimer != null) {
            cancellationTimer.cancel();
        }
    }
}


