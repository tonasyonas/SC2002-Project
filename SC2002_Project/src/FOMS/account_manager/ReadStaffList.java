import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadStaffList {
    public static Map<String, UserCredentials> getStaffCredentials(String filename) {
        Map<String, UserCredentials> credentials = new HashMap<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 6) {
                    String name = parts[0];
                    String loginID = parts[1];
                    String role = parts[2];
                    String gender = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    String branch = parts[5];
    
                    String salt = PasswordUtils.getSalt();
                    String hashedPassword = PasswordUtils.hashPassword("password", salt);
    
                    credentials.put(loginID, new UserCredentials(name, role, gender, age, branch, salt, hashedPassword, true));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return credentials;
    }
}
