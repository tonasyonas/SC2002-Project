package FOMS.account_manager;

import java.util.Map;

public interface PasswordManager {
    boolean verifyPassword(String password, UserCredentials credentials);
    boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap);
}
