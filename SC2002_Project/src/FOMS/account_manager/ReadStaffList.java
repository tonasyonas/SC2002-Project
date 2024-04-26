package FOMS.account_manager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import FOMS.FOMS_entity.*;

/**
 * The ReadStaffList class provides methods for reading staff credentials from a file.
 * It offers functionality to retrieve staff credentials either with or without hashed passwords.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ReadStaffList {
    /**
     * Retrieves staff credentials from a file with hashed passwords.
     * 
     * @param filename The name of the file to read staff credentials from.
     * @return A map containing the login IDs as keys and the corresponding UserCredentials objects as values.
     */
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
                    String genderStr = parts[3]; 
                    Staff.Gender gender; 
        
                    if ("F".equals(genderStr)) {
                        gender = Staff.Gender.F;
                    } else if ("M".equals(genderStr)) {
                        gender = Staff.Gender.M;
                    } else {
                        throw new IllegalArgumentException("Invalid gender value: " + genderStr);
                    }

                    int age = Integer.parseInt(parts[4]);
                    String branch = parts[5];
                    String salt = parts[6];
                    String hashedPassword = parts[7];
                    boolean needsPasswordReset = Boolean.parseBoolean(parts[8]);
    
                    credentials.put(loginID, new UserCredentials(name, role, gender, age, branch, salt, hashedPassword, needsPasswordReset));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return credentials;
    }
        
    /**
     * Retrieves staff credentials from a file without hashed passwords.
     * 
     * @param filename The name of the file to read staff credentials from.
     * @return A map containing the login IDs as keys and the corresponding UserCredentials objects with empty password fields.
     */
    public static Map<String, UserCredentials> getRawStaffCredentials(String filename) {
        Map<String, UserCredentials> credentials = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 5) {
                        String name = parts[0];
                        String loginID = parts[1];
                        String role = parts[2];
                        Staff.Gender gender = "F".equals(parts[3]) ? Staff.Gender.F : Staff.Gender.M;
                        int age = Integer.parseInt(parts[4]);
                        String branch = parts.length > 5 ? parts[5] : "";
                        credentials.put(loginID, new UserCredentials(name, role, gender, age, branch, "", "", false));
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
            return credentials;
        }
    }
    