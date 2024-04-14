import java.util.Map;
import java.util.Scanner;

public class LoginController {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";

    public static void main(String[] args) {
        Map<String, String[]> staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);
        if (staffCredentials == null || staffCredentials.isEmpty()) {
            System.out.println("Failed to load credentials or no credentials available. Please check the file path and permissions.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter your login ID: ");
            String loginID = scanner.nextLine().trim();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (authenticate(staffCredentials, loginID, password)) {
                System.out.println("Login successful. Welcome, " + loginID + "!");
            } else {
                System.out.println("Login failed. Incorrect login ID or password.");
            }
        } finally {
            scanner.close(); // Always ensure resources are closed in a finally block
        }
    }

    private static boolean authenticate(Map<String, String[]> credentials, String loginID, String password) {
        if (credentials.containsKey(loginID)) {
            String[] saltAndHash = credentials.get(loginID);
            return PasswordUtils.verifyPassword(password, saltAndHash[0], saltAndHash[1]);
        }
        return false;
    }
}
