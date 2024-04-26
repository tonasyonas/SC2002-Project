package FOMS.order_manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    /**
     * The {@code GetPaymentMethods} class provides functionalities for retrieving and displaying available payment methods
     * from a file within FOMS. This class is integral for managing payment options, allowing users
     * to view and select from predefined payment methods stored in a text file.
     *
     * <p>This class includes methods to read payment methods from a file, display a specific payment method based on a numerical choice,
     * and display all available payment methods. It is designed to facilitate easy access and manipulation of payment methods for
     * transaction processing.</p>
     *
     * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
     * @version 1.0
     * @since 2024-04-24
     */
public class GetPaymentMethods {
    private static final String PAYMENT_METHODS_FILE = "SC2002_Project/src/FOMS/order_manager/payment_methods.txt";

    /**
     * Retrieves a list of payment methods from the predefined file.
     * Each line in the file is expected to represent a payment method.
     *
     * @return A list of strings, each representing a payment method.
     */

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

    /**
     * Displays a specific payment method based on a numerical choice input by the user.
     * The choice corresponds to the index of the payment method as listed in the payment methods file.
     *
     * @param choice The index number of the payment method to display.
     */
    public static void displayPaymentMethod(int choice){
        // Print the payment method associated with the choice
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENT_METHODS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    int index = Integer.parseInt(parts[0]);
                    if (index == choice) {
                        System.out.println(parts[1]);
                        found = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!found) {
            System.out.println("Invalid payment choice!");
        }
    }

    /**
     * Displays all available payment methods stored in the file.
     * Each payment method is listed with an index number for easy reference and selection.
     */
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
