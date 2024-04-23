package FOMS.account_manager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class InitializationHandler {

    private static final String INIT_FILE = "SC2002_Project/src/FOMS/account_manager/init_config.txt";
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private Map<String, UserCredentials> staffCredentials; // Declare staffCredentials field

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

    public static void setInitialized() {
        try {
            FileIOHandler.writeToFile(INIT_FILE, "initialized=true");
        } catch (IOException e) {
            System.err.println("Error writing to init config file: " + e.getMessage());
        }
    }

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
