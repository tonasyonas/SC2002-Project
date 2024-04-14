import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadStaffList {
    public static Map<String, String[]> getStaffCredentials(String filename) {
        Map<String, String[]> credentials = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    String staffName = parts[0];
                    String loginID = parts[1];
                    // Assume password is staffName for simplicity in this example
                    String password = staffName.replaceAll("\\s+", "").toLowerCase();
                    String salt = PasswordUtils.getSalt();
                    String hashedPassword = PasswordUtils.hashPassword(password, salt);

                    credentials.put(loginID, new String[]{salt, hashedPassword});
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return credentials;
    }
}
