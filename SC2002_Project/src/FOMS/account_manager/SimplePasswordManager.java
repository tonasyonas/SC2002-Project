package FOMS.account_manager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class SimplePasswordManager implements PasswordManager {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";

    @Override
    public boolean verifyPassword(String password, UserCredentials credentials) {
        // Verify password
        return PasswordUtils.verifyPassword(password, credentials.salt, credentials.hashedPassword);
    }

    @Override
    public boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap) {
        // Update password using PasswordUtils
        UserCredentials credentials = credentialsMap.get(loginID);
        if (credentials != null) {
            String newSalt = PasswordUtils.getSalt();
            String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
            credentials.salt = newSalt;
            credentials.hashedPassword = newHashedPassword;
            credentials.needsPasswordReset = false;
            credentialsMap.put(loginID, credentials); // Replace old credentials with updated ones
            return saveCredentialsToFile(credentialsMap, FILE_NAME);
        } else {
            return false; // User not found in credentials map
        }
    }

    // Method to save credentials to file
    private boolean saveCredentialsToFile(Map<String, UserCredentials> credentialsMap, String fileName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
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
