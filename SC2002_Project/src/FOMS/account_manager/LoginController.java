import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class LoginController {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";

    public static void main(String[] args) {
        Map<String, UserCredentials> staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter your login ID: ");
            String loginID = scanner.nextLine().trim();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (login(staffCredentials, loginID, password, scanner)) {
                System.out.println("Login successful. Welcome, " + loginID + "!");
                // Check if password needs to be reset
                UserCredentials credentials = staffCredentials.get(loginID);
                if (credentials != null && credentials.needsPasswordReset) {
                    System.out.println("Would you like to change your password now? (yes/no)");
                    String response = scanner.nextLine().trim();
                    if ("yes".equalsIgnoreCase(response)) {
                        promptPasswordChange(scanner, loginID, staffCredentials);
                    }
                }
            } else {
                System.out.println("Login failed. Incorrect login ID or password.");
            }
        } finally {
            scanner.close();
        }
    }

    private static boolean login(Map<String, UserCredentials> credentialsMap, String loginID, String password, Scanner scanner) {
        UserCredentials credentials = credentialsMap.get(loginID);
        if (credentials != null && PasswordUtils.verifyPassword(password, credentials.salt, credentials.hashedPassword)) {
            return true;
        }
        return false;
    }

    private static void promptPasswordChange(Scanner scanner, String loginID, Map<String, UserCredentials> credentialsMap) {
        System.out.println("Please enter your new password:");
        String newPassword = scanner.nextLine();
        if (updatePassword(loginID, newPassword, credentialsMap)) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Failed to update password. Please try again.");
        }
    }

    private static boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap) {
        String newSalt = PasswordUtils.getSalt();
        String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
        UserCredentials oldCredentials = credentialsMap.get(loginID);
        if (oldCredentials != null) {
            UserCredentials newCredentials = new UserCredentials(
                oldCredentials.name,
                oldCredentials.role,
                oldCredentials.gender,
                oldCredentials.age,
                oldCredentials.branch,
                newSalt,
                newHashedPassword,
                oldCredentials.needsPasswordReset
            );
            credentialsMap.put(loginID, newCredentials); // Replace old credentials with new ones
            return saveCredentialsToFile(credentialsMap);
        } else {
            return false; // User not found in credentials map
        }
    }

    private static boolean saveCredentialsToFile(Map<String, UserCredentials> credentialsMap) {
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
