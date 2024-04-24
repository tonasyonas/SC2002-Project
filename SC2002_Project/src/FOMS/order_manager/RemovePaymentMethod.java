package FOMS.order_manager;

import java.io.*;
import java.util.List;
// import java.util.ArrayList;

/**
 * Provides functionality to remove a payment method from the list of available payment methods.
 * It reads the list of payment methods from a file, removes the specified payment method,
 * renumbers the remaining methods if necessary, and writes the updated list back to the file.
 *
 * The RemovePaymentMethod class contains a single static method, removePaymentMethod, 
 * which takes the name of the payment method to be removed as input.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class RemovePaymentMethod {
    /** The file path of the payment methods file. */
    private static final String PAYMENT_METHODS_FILE = "SC2002_Project/src/FOMS/order_manager/payment_methods.txt";

    /**
     * Removes the specified payment method from the list of payment methods.
     * 
     * @param paymentMethod The name of the payment method to be removed.
     */
    public static void removePaymentMethod(String paymentMethod) {
        List<String> paymentMethods = GetPaymentMethods.getPaymentMethods();

        // Remove the specified payment method from the list
        boolean removed = paymentMethods.removeIf(method -> method.endsWith(paymentMethod));

        // If the payment method was removed, renumber the remaining methods
        if (removed) {
            for (int i = 0; i < paymentMethods.size(); i++) {
                String[] parts = paymentMethods.get(i).split(";");
                parts[0] = Integer.toString(i + 1); // Update the index
                paymentMethods.set(i, String.join(";", parts));
            }
        }

        // Rewrite the payment methods to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYMENT_METHODS_FILE))) {
            for (String method : paymentMethods) {
                writer.write(method);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
