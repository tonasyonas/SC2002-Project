package FOMS.order_manager;
import FOMS.process_manager.*;
import java.util.Scanner;

/**
 * The {@code CollectOrderPage} class manages the interface for collecting orders within FOMS.
 * This class handles the interaction with the user to collect a specified order based on its unique order ID.
 *
 * <p>The class prompts the user to enter an order ID, retrieves the corresponding order using the {@code OrderManager}, and
 * checks the order's current status. Depending on the status, it will allow the order to be collected, inform the user if the
 * order has already been collected, if it is cancelled, or if it is not yet ready for pickup. This class ensures that orders
 * are processed correctly and provides feedback based on the order status.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class CollectOrderPage {
    private Scanner scanner;
    private OrderManager orderManager;

    /**
     * Constructs a {@code CollectOrderPage} with a {@code Scanner} for input and an {@code OrderManager} to manage orders.
     * 
     * @param scanner A {@code Scanner} object for reading user input, typically from the console.
     * @param orderManager An {@code OrderManager} object used to retrieve and process orders based on their IDs.
     */
    public CollectOrderPage(Scanner scanner, OrderManager orderManager) {
        this.scanner = scanner;
        this.orderManager = orderManager;
    }

    /**
     * Starts the page for order collection by prompting the user for an order ID and processing the order based on its status.
     * This method handles various scenarios: when the order is ready for pickup, already collected, cancelled, or not ready.
     */
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
                System.out.println("Order was cancelled because it was not collected within 1 minute.");
            } else {
                System.out.println("Order is not ready for pickup. Current status: " + status);
            }
        } else {
            System.out.println("Order ID not found.");
        }
}
}
