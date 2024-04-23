package FOMS.process_manager;

import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import FOMS.order_manager.Order;

public class BranchStaffPage {


    public static void startBranchStaffPage(String branch) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Display Orders");
            System.out.println("2. Display New Orders");
            System.out.println("3. View Specific Order Details");
            System.out.println("4. Process Orders");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline.

            switch (choice) {
                case 1:
                    DisplayOrder.displayOrders(branch); // Pass the branch to the display orders method
                    break;
                case 2:
                    DisplayNewOrders.displayNewOrders(branch); 
                    break;
                case 3:
                    viewSpecificOrderDetails(); // View details of a specific order
                    break;
                case 4:
                    processOrders(); // Processing might also be branch-specific
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        scanner.close();
    }


    public static void processOrders() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the order ID to process: ");
        String orderId = scanner.nextLine();
        List<Order> updatedOrderList = ProcessOrder.processOrderID(orderId, "Ready for Pickup");
        if (updatedOrderList != null) {
            System.out.println("Order processed successfully.");
        } else {
            System.out.println("Order not found or an error occurred.");
        }
    }

    public static void viewSpecificOrderDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the order ID to view details: ");
        String orderId = scanner.nextLine();
        DisplayOrder.viewOrderDetails(orderId);
    }
}
