package FOMS.process_manager;

import FOMS.order_manager.Order;

import java.util.Scanner;
import java.util.List;

/**
 * The {@code BranchStaffPage} class represents the user interface for branch staff members.
 * It allows staff members to interact with orders, display them, and process them according to their status and needs.
 * This class primarily deals with the command-line interface for the branch staff operations.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 * 
 */

public class BranchStaffPage {

    /**
     * Starts the interface for branch staff members.
     * Provides options for staff to display and process orders.
     * 
     * @param branch The branch name to which the staff belongs and where the orders are managed.
     */

    public static void startBranchStaffPage(String branch) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        int choiceint;
        do {
            System.out.println("Choose an option:");
            System.out.println("0. Exit");
            System.out.println("1. Display Orders");
            System.out.println("2. Display New Orders");
            System.out.println("3. Process Orders");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            try{
                choiceint = Integer.parseInt(choice);
            }
            catch (Exception e){
                System.out.println("Invalid input. Please enter a number.");
                continue;
            } 

            switch (choiceint) {
                case 1:
                    DisplayOrder.displayOrders(branch); // Pass the branch to the display orders method
                    break;
                case 2:
                    DisplayNewOrders.displayNewOrders(branch); 
                    break;
                case 3:
                    processOrders(); // Processing might also be branch-specific
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number (0-3).");
                    break;
            }
        } while (!choice.equals("0"));

    }


    /**
     * Processes the orders based on the order ID provided by the user.
     * It displays the order details and asks for confirmation before processing the order.
     */
    public static void processOrders() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the order ID to process: ");
        String orderId = scanner.nextLine();
        DisplayOrder.viewOrderDetails(orderId);
        System.out.println("Confirm that you want to process this order (Yes or No)");
        String boolconfirmation = scanner.nextLine().toLowerCase();
        if (boolconfirmation.equals("yes")){
            List<Order> updatedOrderList = ProcessOrder.processOrderID(orderId, "Ready for Pickup");
            if (updatedOrderList != null) {
                System.out.println("Order processed successfully.");
            } else {
                System.out.println("Order not found or an error occurred.");
            }
        }
    }
  
}
