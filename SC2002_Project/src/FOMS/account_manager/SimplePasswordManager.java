package FOMS.account_manager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * The SimplePasswordManager class implements the PasswordManager interface to manage user passwords.
 * 
 * It provides methods to verify passwords and update passwords for user credentials stored in a file.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class SimplePasswordManager implements PasswordManager {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";

    /**
     * Verifies whether the provided password matches the stored password for the given credentials.
     * 
     * @param password The password to verify.
     * @param credentials The user credentials containing the stored password.
     * @return true if the password is verified, false otherwise.
     */
    @Override
    public boolean verifyPassword(String password, UserCredentials credentials) {
        return PasswordUtils.verifyPassword(password, credentials.salt, credentials.hashedPassword);
    }

    /**
     * Updates the password for the specified user login ID.
     * 
     * @param loginID The login ID of the user whose password needs to be updated.
     * @param newPassword The new password to set.
     * @param credentialsMap The map containing user credentials.
     * @return true if the password is updated successfully, false otherwise.
     */
    @Override
    public boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap) {
        UserCredentials credentials = credentialsMap.get(loginID);
        if (credentials != null) {
            String newSalt = PasswordUtils.getSalt();
            String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
            credentials.salt = newSalt;
            credentials.hashedPassword = newHashedPassword;
            credentials.needsPasswordReset = false;
            credentialsMap.put(loginID, credentials); 
            return saveCredentialsToFile(credentialsMap, FILE_NAME);
        } else {
            return false;
        }
    }

    /**
     * Saves the updated user credentials to the specified file.
     * 
     * @param credentialsMap The map containing the updated user credentials.
     * @param fileName The name of the file to which the credentials will be saved.
     * @return true if the credentials are successfully saved, false otherwise.
     */
    private boolean saveCredentialsToFile(Map<String, UserCredentials> credentialsMap, String fileName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, UserCredentials> entry : credentialsMap.entrySet()) {
                UserCredentials cred = entry.getValue();
                out.printf("%s;%s;%s;%s;%d;%s;%s;%s;%s\n",
                        cred.name, entry.getKey(), cred.role, cred.gender,
                        cred.age, cred.branch, cred.salt, cred.hashedPassword, cred.needsPasswordReset);
            }
            out.flush();  
        } catch (IOException e) {
            System.err.println("Failed to save credentials: " + e.getMessage());
            return false;
        }
        return true;
    }
}
