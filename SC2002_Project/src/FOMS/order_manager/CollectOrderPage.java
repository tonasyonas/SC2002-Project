package FOMS.order_manager;
import FOMS.process_manager.*;
import java.util.Scanner;

public class CollectOrderPage {
    private Scanner scanner;
    private OrderManager orderManager;

    public CollectOrderPage(Scanner scanner, OrderManager orderManager) {
        this.scanner = scanner;
        this.orderManager = orderManager;
    }

    // Method to start the page
    public void startPage() {
        // Ask the user to input the order they want to collect
        System.out.println("Enter the orderID you want to collect:");
        String orderID = scanner.nextLine();
        
        // Process the order (you may need to implement this)
        Order orderToCollect = orderManager.getOrderById(orderID);
        if (orderToCollect != null) {
            // Check the status of the order
            String status = orderToCollect.getStatus();
            if ("Ready for Pickup".equals(status)) {
                // Process the order (you may need to implement this)
                ProcessOrder.processOrderID(orderID, "Completed");
                System.out.println("Order collected successfully.");
            } else if ("Completed".equals(status)) {
                System.out.println("Order has already been collected.");
            } else if ("Cancelled".equals(status)) {
                System.out.println("Order was cancelled because it was not collected within 5 minutes.");
            } else {
                System.out.println("Order is not ready for pickup. Current status: " + status);
            }
        } else {
            System.out.println("Order ID not found.");
        }
}
}
