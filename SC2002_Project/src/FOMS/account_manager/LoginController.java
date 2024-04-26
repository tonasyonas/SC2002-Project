/**
 * The LoginController class manages user login and password-related operations.
 */
package FOMS.account_manager;

import java.util.Map;
import java.util.Scanner;

public class LoginController {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private Map<String, UserCredentials> staffCredentials;
    private PasswordManager passwordManager;

    /**
     * Constructs a new LoginController instance with the specified PasswordManager.
     * Initializes staff passwords if the system is not already initialized.
     * Loads staff credentials after initialization.
     * @param passwordManager The PasswordManager instance to use for password operations.
     */
    public LoginController(PasswordManager passwordManager) {
        this.passwordManager = passwordManager;
        if (!InitializationHandler.isInitialized()) {
            InitializationHandler initializationHandler = new InitializationHandler();
            initializationHandler.initializeStaffPasswords();
            InitializationHandler.setInitialized();
        }
        this.staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);   
    }



    /**
     * Attempts to log in a user with the specified login ID and password.
     * @param loginID The login ID of the user.
     * @param password The password entered by the user.
     * @param scanner The Scanner object for user input.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String loginID, String password, Scanner scanner) {
        UserCredentials credentials = staffCredentials.get(loginID);
        if (credentials != null && passwordManager.verifyPassword(password, credentials)) {
            return true;
        }
        return false;
    }

    /**
     * Prompts the user to change their password.
     * @param scanner The Scanner object for user input.
     * @param loginID The login ID of the user.
     */
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
