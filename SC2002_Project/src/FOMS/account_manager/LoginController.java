package FOMS.account_manager;

import java.util.Map;
import java.util.Scanner;

public class LoginController {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private Map<String, UserCredentials> staffCredentials;
    private PasswordManager passwordManager;

    public static void main(String[] args) {
        LoginController controller = new LoginController(new SimplePasswordManager());
        controller.run(); // Handle all logic after initialization check inside this method
    }

    public LoginController(PasswordManager passwordManager) {
        this.passwordManager = passwordManager;
        if (!InitializationHandler.isInitialized()) {
            InitializationHandler initializationHandler = new InitializationHandler();
            initializationHandler.initializeStaffPasswords();
            InitializationHandler.setInitialized();
        }
        this.staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME); // Load credentials after initialization
    }

    private void run() {
        // Any additional code to run after initialization
    }

    public boolean login(String loginID, String password, Scanner scanner) {
        UserCredentials credentials = staffCredentials.get(loginID);
        if (credentials != null && passwordManager.verifyPassword(password, credentials)) {
            return true;
        }
        return false;
    }

    public void promptPasswordChange(Scanner scanner, String loginID) {
        System.out.println("Please enter your new password:");
        String newPassword = scanner.nextLine();
        if (passwordManager.updatePassword(loginID, newPassword, staffCredentials)) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Failed to update password. Please try again.");
        }
    }
}
