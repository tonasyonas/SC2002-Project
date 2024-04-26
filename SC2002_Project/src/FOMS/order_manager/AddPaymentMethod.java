package FOMS.order_manager;

import java.io.*;
import java.util.List;

/**
 * The {@code AddPaymentMethod} class provides functionality to add new payment methods to the system. This class is used
 * within FOMS to dynamically update and manage different payment options available to users.
 *
 * <p>The class reads existing payment methods, determines the next available index, and appends the new payment method
 * to a designated file. This facilitates the addition of new payment options without disrupting existing ones, enhancing
 * the flexibility and scalability of payment processing.</p>
 *
 * <p>Usage of this class involves invoking its static method {@code addPaymentMethod} with the new payment method as an argument.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class AddPaymentMethod {
    private static final String PAYMENT_METHODS_FILE = "SC2002_Project/src/FOMS/order_manager/payment_methods.txt";

    /**
     * Adds a new payment method to the list of available payment methods stored in a text file.
     * This method first retrieves the current list of payment methods to determine the next index,
     * then writes the new payment method entry at the end of the file.
     *
     * @param paymentMethod The new payment method to add, described by a string.
     */
    public static void addPaymentMethod(String paymentMethod) {
        List<String> paymentMethods = GetPaymentMethods.getPaymentMethods();

        // Determine the next available index
        int nextIndex = paymentMethods.size() + 1;

        // Append the new payment method with the updated number
        String newPaymentMethodEntry = nextIndex + ";" + paymentMethod;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYMENT_METHODS_FILE, true))) {
            writer.write(newPaymentMethodEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}