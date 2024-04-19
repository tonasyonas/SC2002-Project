package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class LoginController {
private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
Map<String, UserCredentials> staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);

    // public static void main(String[] args) {
    //     Map<String, UserCredentials> staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);
    //     Scanner scanner = new Scanner(System.in);
    
    //     try {
    //         System.out.println("Are you a customer or a staff member?");
    //         System.out.print("Enter 'customer' or 'staff': ");
    //         String userType = scanner.nextLine().trim().toLowerCase();
    
    //         if (userType.equals("customer")) {
    //             // Allow customers to proceed to the ordering system without logging in
    //             System.out.println("Welcome to the ordering system!");
    //             // Proceed to the ordering system
    //         } else if (userType.equals("staff")) {
    //             System.out.print("Enter your login ID: ");
    //             String loginID = scanner.nextLine().trim();
    //             UserCredentials credentials = staffCredentials.get(loginID);
                
                if (credentials != null) {
                    // Prompt staff members to log in
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    if (login(staffCredentials, loginID, password, scanner)) {
                        System.out.println("Login successful. Welcome, " + loginID + "!");
                        // Check if password needs to be reset
                        if (credentials.needsPasswordReset) {
                            System.out.println("Would you like to change your password now? (yes/no)");
                            String response = scanner.nextLine().trim();
                            if ("yes".equalsIgnoreCase(response)) {
                                promptPasswordChange(scanner, loginID, staffCredentials);
                            }
                        }
                       
                        // Proceed to the ordering system
                    } else {
                        System.out.println("Login failed. Incorrect login ID or password.");
                    }
                } else {
                    System.out.println("Staff member not found. Please try again.");
                }
            } else {
                System.out.println("Invalid user type. Please enter 'customer' or 'staff'.");
            }
        } finally {
            scanner.close();
        }
    }
=======
    //             if (credentials != null) {
    //                 // Prompt staff members to log in
    //                 System.out.print("Enter your password: ");
    //                 String password = scanner.nextLine();
    //                 if (login(staffCredentials, loginID, password, scanner)) {
    //                     System.out.println("Login successful. Welcome, " + loginID + "!");
    //                     // Check if password needs to be reset
    //                     if (credentials.needsPasswordReset) {
    //                         System.out.println("Would you like to change your password now? (yes/no)");
    //                         String response = scanner.nextLine().trim();
    //                         if ("yes".equalsIgnoreCase(response)) {
    //                             promptPasswordChange(scanner, loginID, staffCredentials);
    //                         }
    //                     }
    //                     // Proceed to the ordering system
    //                 } else {
    //                     System.out.println("Login failed. Incorrect login ID or password.");
    //                 }
    //             } else {
    //                 System.out.println("Staff member not found. Please try again.");
    //             }
    //         } else {
    //             System.out.println("Invalid user type. Please enter 'customer' or 'staff'.");
    //         }
    //     } finally {
    //         scanner.close();
    //     }
    // }
    
    public static boolean login(Map<String, UserCredentials> credentialsMap, String loginID, String password, Scanner scanner) {
        UserCredentials credentials = credentialsMap.get(loginID);
        if (credentials != null && PasswordUtils.verifyPassword(password, credentials.salt, credentials.hashedPassword)) {
            return true;
        }
        return false;
    }

    public static void promptPasswordChange(Scanner scanner, String loginID, Map<String, UserCredentials> credentialsMap) {
        System.out.println("Please enter your new password:");
        String newPassword = scanner.nextLine();
        if (updatePassword(loginID, newPassword, credentialsMap)) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Failed to update password. Please try again.");
        }
    }

    public static boolean updatePassword(String loginID, String newPassword, Map<String, UserCredentials> credentialsMap) {
        String newSalt = PasswordUtils.getSalt();
        String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
        UserCredentials oldCredentials = credentialsMap.get(loginID);
        if (oldCredentials != null) {
            UserCredentials newCredentials = new UserCredentials(
                oldCredentials.name,
                oldCredentials.role,
                oldCredentials.gender,
                oldCredentials.age,
                oldCredentials.branch,
                newSalt,
                newHashedPassword,
                oldCredentials.needsPasswordReset = false
            );
            credentialsMap.put(loginID, newCredentials); // Replace old credentials with new ones
            return saveCredentialsToFile(credentialsMap);
        } else {
            return false; // User not found in credentials map
        }
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
