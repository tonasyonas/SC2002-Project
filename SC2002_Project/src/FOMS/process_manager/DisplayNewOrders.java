package FOMS.process_manager;

import java.util.List;
import java.io.IOException;
import FOMS.order_manager.*;

public class DisplayNewOrders {

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
