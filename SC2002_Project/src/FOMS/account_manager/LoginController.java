package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private static final String INIT_FILE = "SC2002_Project/src/FOMS/account_manager/init_config.txt";
    private Map<String, UserCredentials> staffCredentials;

    public static void main(String[] args) {
        LoginController controller = new LoginController();
        controller.run(); // Handle all logic after initialization check inside this method
    }

    public LoginController() {
        if (!isInitialized()) {
            initializeStaffPasswords();
            setInitialized();
        }
        loadStaffCredentials(); // Load credentials only after ensuring they are initialized
    }

    private void run() {
        // Any additional code to run after initialization
    }

    private void loadStaffCredentials() {
        this.staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);
    }

    private boolean isInitialized() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(INIT_FILE));
            for (String line : lines) {
                if ("initialized=true".equals(line.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading init config file: " + e.getMessage());
        }
        return false;
    }

    private void setInitialized() {
        try {
            Files.write(Paths.get(INIT_FILE), "initialized=true".getBytes());
        } catch (IOException e) {
            System.err.println("Error writing to init config file: " + e.getMessage());
        }
    }

    private void initializeStaffPasswords() {
        // Assuming here that we have a method to create initial credentials
        // If you are starting from scratch, you would create these from a predefined list
        this.staffCredentials = ReadStaffList.getRawStaffCredentials(FILE_NAME); // Adjust as per previous advice if necessary
        for (Map.Entry<String, UserCredentials> entry : this.staffCredentials.entrySet()) {
            String salt = PasswordUtils.getSalt();
            String hashedPassword = PasswordUtils.hashPassword("password", salt);
            UserCredentials credentials = entry.getValue();
            credentials.salt = salt;
            credentials.hashedPassword = hashedPassword;
            credentials.needsPasswordReset = true;
        }
        saveCredentialsToFile(this.staffCredentials);
    }

    public static boolean login(Map<String, UserCredentials> credentialsMap, String loginID, String password, Scanner scanner) {
        UserCredentials credentials = credentialsMap.get(loginID);
        if (credentials != null && PasswordUtils.verifyPassword(password, credentials.salt, credentials.hashedPassword)) {
            return true;
        }
        return false;
    }

    public static void promptPasswordChange(Scanner scanner, String loginID, Map<String, UserCredentials> credentialsMap) {
        System.out.println("Please enter your new password:");
        String newPassword = scanner.nextLine();
        if (updatePassword(loginID, newPassword, credentialsMap)) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Failed to update password. Please try again.");
        }
    }

    public static boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap) {
        String newSalt = PasswordUtils.getSalt();
        String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
        UserCredentials oldCredentials = credentialsMap.get(loginID);
        if (oldCredentials != null) {
            oldCredentials.salt = newSalt;
            oldCredentials.hashedPassword = newHashedPassword;
            oldCredentials.needsPasswordReset = false;
            credentialsMap.put(loginID, oldCredentials); // Replace old credentials with updated ones
            return saveCredentialsToFile(credentialsMap);
        } else {
            return false; // User not found in credentials map
        }
    }

    public static boolean saveCredentialsToFile(Map<String, UserCredentials> credentialsMap) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, UserCredentials> entry : credentialsMap.entrySet()) {
                UserCredentials cred = entry.getValue();
                out.printf("%s;%s;%s;%s;%d;%s;%s;%s;%s\n",
                    cred.name, entry.getKey(), cred.role, cred.gender,
                    cred.age, cred.branch, cred.salt, cred.hashedPassword, cred.needsPasswordReset);
            }
            out.flush();  // Ensuring data is written to the file
        } catch (IOException e) {
            System.err.println("Failed to save credentials: " + e.getMessage());
            return false;
        }
        return true;
    }
}
