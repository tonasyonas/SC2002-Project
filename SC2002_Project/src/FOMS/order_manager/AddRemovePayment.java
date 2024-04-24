package FOMS.order_manager;

import java.util.Scanner;

/**
 * The {@code AddRemovePayment} class provides a user interface for adding or removing payment methods in the FOMS. It utilizes static methods to interact with the underlying payment method management
 * classes, {@code AddPaymentMethod} and {@code RemovePaymentMethod}, to modify the list of available payment methods.
 *
 * <p>This class is primarily designed to be used in administrative settings where modifications to payment methods
 * are necessary. It guides the user through a console-based menu system to choose whether to add or remove a payment method.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class AddRemovePayment {

    /**
     * Provides a menu-driven approach to add or remove payment methods based on user input.
     * This method reads user input from the passed {@code Scanner} object to determine the user's choice,
     * and then prompts for further input based on the selected option.
     *
     * @param scanner A {@code Scanner} object for reading input from the console.
     */
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
