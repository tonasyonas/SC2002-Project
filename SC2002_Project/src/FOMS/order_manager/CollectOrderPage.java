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
            if ("Ready for Pickup".equals(orderToCollect.getStatus())) {
                // Process the order (you may need to implement this)
                ProcessOrder.processOrderID(orderID, "Completed");
                System.out.println("Order collected successfully.");
            } else {
                System.out.println("Order is not ready for pickup.");
            }
        } else {
            System.out.println("Order ID not found.");
        }
    }

}
