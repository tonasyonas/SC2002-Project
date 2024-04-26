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
    