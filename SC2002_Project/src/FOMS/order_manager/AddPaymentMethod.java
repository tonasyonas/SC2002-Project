package FOMS.order_manager;

import java.io.*;

public class AddPaymentMethod {
    private static final String PAYMENT_METHODS_FILE = "payment_methods.txt";

    public static void addPaymentMethod(String paymentMethod) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYMENT_METHODS_FILE, true))) {
            writer.write(paymentMethod);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
