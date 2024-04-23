package FOMS.order_manager;

import java.util.Scanner;

public class AddRemovePayment {

    public static void addRemovePayment(Scanner scanner) {
        // Do not create a new Scanner here; use the one passed as an argument
        System.out.println("Choose an option:");
        System.out.println("0. Exit");
        System.out.println("1. Add Payment Method");
        System.out.println("2. Remove Payment Method");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter Payment Method: ");
        String paymentmethod = scanner.nextLine();

        switch (choice) {
            case 1:
                AddPaymentMethod.addPaymentMethod(paymentmethod);;
                break;
            case 2:
                RemovePaymentMethod.removePaymentMethod(paymentmethod);;
                break;
        }
    }
}
