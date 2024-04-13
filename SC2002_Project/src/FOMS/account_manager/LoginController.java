
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginController {
    public static void main(String[] args) {
        // Create a HashMap to store staff ID-password pairs
        Map<String, String> staffCredentials = new HashMap<>();
        staffCredentials.put("staff123", "password123");
        staffCredentials.put("johnDoe", "test456");
        // Add more staff IDs and passwords as needed

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter staff ID
        System.out.print("Enter your login ID: ");
        String staffId = scanner.nextLine();

        // Prompt the user to enter password
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Check if staff ID exists in the HashMap and if the corresponding password matches
        if (staffCredentials.containsKey(staffId) && staffCredentials.get(staffId).equals(password)) {
            System.out.println("Login successful. Welcome, " + staffId + "!");
        } else {
            System.out.println("Incorrect staff ID or password. Please try again.");
        }

        // Close the scanner
        scanner.close();
    }
}
