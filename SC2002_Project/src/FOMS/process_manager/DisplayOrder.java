package FOMS.process_manager;

import java.io.IOException;
import java.util.List;

import FOMS.order_manager.*;

public abstract class DisplayOrder {

    public static void displayOrders() {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt"; // Replace this with the actual file path
    
        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : orders) {
                System.out.println(order);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void displayOrders(String branch) {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt"; // Replace this with the actual file path
    
        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : orders) {
                // Check if the order's branch matches the staff's branch
                if (!order.getOrderItems().isEmpty() && order.getOrderItems().get(0).getMenuItem().getBranch().equals(branch)) {
                    System.out.println("Order ID: " + order.getOrderId());
                    System.out.println("Status: " + order.getStatus());
                    System.out.println("Order Type: " + order.getOrderType());
                    System.out.println("Branch: " + branch);
                    System.out.println("Items:");
                    for (OrderItem item : order.getOrderItems()) {
                        System.out.println(" - Item: " + item.getMenuItem().getItem());
                        System.out.println("   Quantity: " + item.getQuantity());
                        System.out.println("   Price: $" + String.format("%.2f", item.getMenuItem().getCost()));
                        if (item.getCustomization() != null && !item.getCustomization().isEmpty()) {
                            System.out.println("   Customizations: " + item.getCustomization());
                        }
                    }
                    System.out.println("Total: $" + String.format("%.2f", order.getTotal()));
                    System.out.println("---------------------------");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    
    public static void viewOrderDetails(String orderId) {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt"; // Replace this with the actual file path

        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            boolean orderFound = false;

            for (Order order : orders) {
                if (order.getOrderId().equals(orderId)) {
                    orderFound = true;
                    System.out.println("Order ID: " + order.getOrderId());
                    System.out.println("Status: " + order.getStatus());
                    System.out.println("Order Type: " + order.getOrderType());
                    System.out.println("Branch: " + order.getOrderItems().get(0).getMenuItem().getBranch());
                    System.out.println("Items:");
                    for (OrderItem item : order.getOrderItems()) {
                        System.out.println(" - Item: " + item.getMenuItem().getItem());
                        System.out.println("   Quantity: " + item.getQuantity());
                        System.out.println("   Price: $" + String.format("%.2f", item.getMenuItem().getCost()));
                        if (item.getCustomization() != null && !item.getCustomization().isEmpty()) {
                            System.out.println("   Customizations: " + item.getCustomization());
                        }
                    }
                    System.out.println("Total: $" + String.format("%.2f", order.getTotal()));
                    System.out.println("---------------------------");
                    break; // Exit the loop once the order is found
                }
            }

            if (!orderFound) {
                System.out.println("Order not found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }



    
    

}
