package FOMS.process_manager;

import FOMS.order_manager.*;

import java.util.List;
import java.io.IOException;

/**
 * The {@code DisplayNewOrders} class contains functionality to display newly placed orders
 * for a particular branch. It reads orders from a file and prints details of new orders
 * to the console.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

 public class DisplayNewOrders {

    /**
     * Displays new orders for a specific branch.
     * It reads the list of orders from a file and filters orders by branch and status.
     * Only orders that are new and belong to the specified branch are displayed.
     *
     * @param branch The name of the branch for which to display new orders.
     *               This should be the branch where the staff member is located.
     */

    public static void displayNewOrders(String branch) {
        
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt"; // Replace this with the actual file path

        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : orders) {
                if (order.getOrderItems().get(0).getMenuItem().getBranch().equalsIgnoreCase(branch) &&
                    order.getStatus().equalsIgnoreCase(OrderStatus.NEW.toString())) {
                    // Display new orders related to the specified branch
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
    

}
