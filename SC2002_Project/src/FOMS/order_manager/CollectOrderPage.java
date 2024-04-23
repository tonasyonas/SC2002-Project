package FOMS.order_manager;
import FOMS.process_manager.*;
import java.util.Scanner;

public class CollectOrderPage {
    private Scanner scanner;

    // Constructor that accepts a Scanner object
    public CollectOrderPage(Scanner scanner) {
        this.scanner = scanner;
    }

    // Method to start the page
    public void startPage() {
        // Ask the user to input the order they want to collect
        System.out.println("Enter the orderID you want to collect:");
        String orderID = scanner.nextLine();
        
        // Process the order (you may need to implement this)
        ProcessOrder.processOrderID(orderID, "Completed");
    }
}
