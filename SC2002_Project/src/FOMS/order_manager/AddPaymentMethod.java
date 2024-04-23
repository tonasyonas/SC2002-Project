package FOMS.order_manager;

import java.io.*;
import java.util.List;

public class AddPaymentMethod {
    private static final String PAYMENT_METHODS_FILE = "SC2002_Project/src/FOMS/order_manager/payment_methods.txt";

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