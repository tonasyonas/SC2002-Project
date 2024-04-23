package FOMS.process_manager;

import java.util.Scanner;
import java.util.List;
import FOMS.order_manager.Order;
import FOMS.process_manager.OrderTimeoutScheduler;

public class BranchStaffPage {

    // Update the method signature to accept a list of orders
    public static void startBranchStaffPage(String branch, List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Start the scheduler to automatically update order statuses based on timeout rules
        OrderTimeoutScheduler.startScheduler(orders);

        while (!exit) {
            System.out.println("1. Display Orders");
            System.out.println("2. Display New Orders");
            System.out.println("3. Process Orders");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline.

            switch (choice) {
                case 1:
                    DisplayOrder.displayOrders(branch);
                    break;
                case 2:
                    DisplayNewOrders.displayNewOrders(branch);
                    break;
                case 3:
                    processOrders(orders, scanner); // Pass the list of orders and scanner to process orders
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }

        scanner.close();
        OrderTimeoutScheduler.stopScheduler(); // Properly stop the scheduler when exiting
    }

    // Modify this method to use the list of orders and handle updates
    public static void processOrders(List<Order> orders, Scanner scanner) {
        System.out.print("Enter the order ID to process: ");
        String orderId = scanner.nextLine();
        Order order = orders.stream()
                            .filter(o -> o.getOrderId().equals(orderId))
                            .findFirst()
                            .orElse(null);

        if (order != null) {
            DisplayOrder.viewOrderDetails(orderId);
            System.out.println("Confirm that you want to process this order (Yes or No)");
            String confirmation = scanner.nextLine().toLowerCase();
            if ("yes".equals(confirmation)) {
                order.setStatus("Ready for Pickup");
                System.out.println("Order processed successfully.");
            }
        } else {
            System.out.println("Order not found or an error occurred.");
        }
    }
}
