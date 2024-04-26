package FOMS.process_manager;

import java.io.IOException;
import java.util.List;

import FOMS.order_manager.*;

/**
 * The {@code DisplayOrder} abstract class provides static methods to display orders from a file.
 * This class can be used to show all orders, orders specific to a branch, or the details of a single order.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public abstract class DisplayOrder {

    /**
     * Displays all orders from the file.
     * Each order is printed using its {@code toString} method.
     * 
     */

    public static void displayOrders() {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt";  
    
        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : orders) {
                System.out.println(order);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Displays orders related to a specific branch from the file.
     * It filters orders by the branch associated with each order's menu items.
     * 
     * @param branch The name of the branch to filter the orders by.
     */

    public static void displayOrders(String branch) {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt"; 
    
        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : orders) {
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
    
    /**
     * Displays the details of a single order identified by its order ID.
     * If the order is found, its details are printed; otherwise, a message indicating
     * the order was not found is printed.
     * 
     * @param orderId The ID of the order to display.
     */
    public static void viewOrderDetails(String orderId) {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt";

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
                    break; 
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
