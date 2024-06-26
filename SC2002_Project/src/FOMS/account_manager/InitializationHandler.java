/**
 * The InitializationHandler class provides methods for initializing and managing staff credentials.
 */
package FOMS.account_manager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * The InitializationHandler class provides methods for initializing and managing staff credentials.
 * 
 * It includes functionality to check if the system has been initialized, set the system as initialized,
 * initialize staff passwords, and save credentials to a file.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class InitializationHandler {

    private static final String INIT_FILE = "SC2002_Project/src/FOMS/account_manager/init_config.txt";
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private Map<String, UserCredentials> staffCredentials; 

    /**
     * Checks if the system has been initialized.
     * @return true if the system is initialized, false otherwise.
     */
    public static boolean isInitialized() {
        try {
            List<String> lines = FileIOHandler.readLines(INIT_FILE);
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

    /**
     * Sets the system as initialized.
     */
    public static void setInitialized() {
        try {
            FileIOHandler.writeToFile(INIT_FILE, "initialized=true");
        } catch (IOException e) {
            System.err.println("Error writing to init config file: " + e.getMessage());
        }
    }

    /**
     * Initializes staff passwords.
     */
    public void initializeStaffPasswords() {
        this.staffCredentials = ReadStaffList.getRawStaffCredentials(FILE_NAME);
        for (Map.Entry<String, UserCredentials> entry : this.staffCredentials.entrySet()) {
            String salt = PasswordUtils.getSalt();
            String hashedPassword = PasswordUtils.hashPassword("password", salt);
            UserCredentials credentials = entry.getValue();
            credentials.setSalt(salt);
            credentials.setHashedPassword(hashedPassword);
            credentials.setNeedsPasswordReset(true);
        }
        saveCredentialsToFile(this.staffCredentials);
    }

    /**
     * Saves credentials to a file.
     * @param credentialsMap The map containing user credentials.
     * @return true if saving is successful, false otherwise.
     */
    public static boolean saveCredentialsToFile(Map<String, UserCredentials> credentialsMap) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
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
