package FOMS.order_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetPaymentMethods {
    private static final String PAYMENT_METHODS_FILE = "payment_methods.txt";

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
        for (int i = 0; i < paymentMethods.size(); i++) {
            System.out.println((i + 1) + ". " + paymentMethods.get(i));
        }
    }

}
