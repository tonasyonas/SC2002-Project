package FOMS.account_manager;

import java.util.Map;

/**
 * The PasswordManager interface provides methods for password verification and update.
 * Implementing classes must define methods to verify passwords and update passwords for user credentials.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface PasswordManager {
    /**
     * Verifies if the provided password matches the stored password in the user credentials.
     * 
     * @param password The password to verify.
     * @param credentials The UserCredentials object containing the stored password.
     * @return true if the provided password matches the stored password, false otherwise.
     */
    boolean verifyPassword(String password, UserCredentials credentials);

    /**
     * Updates the password for the specified login ID in the credentials map.
     * 
     * @param loginID The login ID of the user whose password needs to be updated.
     * @param newPassword The new password to set.
     * @param credentialsMap The map containing user credentials.
     * @return true if the password is updated successfully, false otherwise.
     */
    boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap);
}
