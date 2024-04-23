package FOMS.order_manager;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orderItems;
    private String orderId;
    private double total;
    private String status; // Possible values: "New", "Ready to Pickup", "Completed"
    private String orderType;

    // Constructor
    public Order(String orderId, String status, double total, String orderType, List<OrderItem> orderItems ) {
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.status = status; 
        this. orderType = orderType;
        this.orderItems = orderItems;
        this.total=total;
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

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    // Method to get the order type
    public String getOrderType() {
        return orderType;
    }

 
}

