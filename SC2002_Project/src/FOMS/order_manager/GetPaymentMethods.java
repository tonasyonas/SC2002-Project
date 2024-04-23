package FOMS.order_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetPaymentMethods {
    private static final String PAYMENT_METHODS_FILE = "SC2002_Project/src/FOMS/order_manager/payment_methods.txt";

    public static List<String> getPaymentMethods() {
        List<String> paymentMethods = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENT_METHODS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                paymentMethods.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }

    public static void displayPaymentMethods() {
        List<String> paymentMethods = getPaymentMethods();
        for (String method : paymentMethods) {
            String[] parts = method.split(";");
            if (parts.length >= 2) {
                System.out.println(parts[0] + ". " + parts[1]);
            }
        }
    }

}
