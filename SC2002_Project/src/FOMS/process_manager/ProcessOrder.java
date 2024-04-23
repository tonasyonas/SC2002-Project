package FOMS.process_manager;

import java.util.Scanner;

public class ProcessOrder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Orders");
            System.out.println("2. Process Orders");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    DisplayOrder.displayOrders();
                    break;
                case 2:
                    System.out.println("Enter Order ID:");
                    int selectedID = scanner.nextInt();

                    break;
               
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

}
