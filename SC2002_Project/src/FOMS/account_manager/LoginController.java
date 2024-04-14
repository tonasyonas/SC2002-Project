import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
            if (credentials.needsPasswordReset) {
                promptPasswordChange(scanner, loginID, credentialsMap);
            }
            return true;
        }
        return false;
    }

    private static void promptPasswordChange(Scanner scanner, String loginID, Map<String, UserCredentials> credentialsMap) {
        System.out.println("You must change your password.");
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        if (updatePassword(loginID, newPassword, credentialsMap)) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Failed to update password. Try again.");
        }
    }

    private static boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap) {
        // Retrieve the current user details from the map
        UserCredentials currentUser = credentialsMap.get(loginID);
        if (currentUser == null) {
            System.out.println("User not found.");
            return false; // Return false if the user does not exist
        }
    
        // Add password complexity check here if needed
        String newSalt = PasswordUtils.getSalt();
        String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
    
        // Create a new UserCredentials object with the updated password and salt
        UserCredentials newCredentials = new UserCredentials(
            currentUser.name, currentUser.role, currentUser.gender, currentUser.age, currentUser.branch,
            newSalt, newHashedPassword, false // Assuming the password reset requirement is now false
        );
    
        // Update the credentials map with the new credentials
        credentialsMap.put(loginID, newCredentials);
        
        // Save changes to the file
        return saveCredentialsToFile(credentialsMap);
    }
    

    private static boolean saveCredentialsToFile(Map<String, UserCredentials> credentialsMap) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, UserCredentials> entry : credentialsMap.entrySet()) {
                UserCredentials cred = entry.getValue();
                out.printf("%s;%s;%s;%s;%d;%s;%s;%s;%s\n", 
                    cred.name, entry.getKey(), cred.role, cred.gender, cred.age, cred.branch, 
                    cred.salt, cred.hashedPassword, cred.needsPasswordReset);
            }
        } catch (IOException e) {
            System.err.println("Failed to save credentials: " + e.getMessage());
            return false;
        }
        return true;
    }
}
