package FOMS.order_manager;

import java.io.*;
import java.util.List;
// import java.util.ArrayList;

public class RemovePaymentMethod {
    private static final String PAYMENT_METHODS_FILE = "SC2002_Project/src/FOMS/order_manager/payment_methods.txt";

    public static void removePaymentMethod(String paymentMethod) {
        List<String> paymentMethods = GetPaymentMethods.getPaymentMethods();
        paymentMethods.remove(paymentMethod);

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
