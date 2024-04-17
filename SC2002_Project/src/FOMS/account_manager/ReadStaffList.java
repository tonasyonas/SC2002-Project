package FOMS.account_manager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import FOMS.FOMS_entity.*;

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
                    String genderStr = parts[3]; // Retrieve string representation of gender
                    Staff.Gender gender; // Declare the variable to hold the converted gender
        
                    // Convert string representation to Staff.Gender enum
                    if ("F".equals(genderStr)) {
                        gender = Staff.Gender.F;
                    } else if ("M".equals(genderStr)) {
                        gender = Staff.Gender.M;
                    } else {
                        // Handle invalid or unknown gender values
                        throw new IllegalArgumentException("Invalid gender value: " + genderStr);
                    }

                    int age = Integer.parseInt(parts[4]);
                    String branch = parts[5];
                    String salt = parts[6];
                    String hashedPassword = parts[7];
                    boolean needsPasswordReset = Boolean.parseBoolean(parts[8]); // Read needsPasswordReset from file
    
                    credentials.put(loginID, new UserCredentials(name, role, gender, age, branch, salt, hashedPassword, needsPasswordReset));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return credentials;
    }
}

