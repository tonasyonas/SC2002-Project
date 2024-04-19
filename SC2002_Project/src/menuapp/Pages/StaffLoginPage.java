package menuapp.Pages;

import FOMS.account_manager.*;
import java.util.Map;
import java.util.Scanner;

public class StaffLoginPage {

    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private Map<String, UserCredentials> staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);

    public StaffLoginPage() {
        System.out.println("Welcome to the staff login system!");
    }

    public void StaffLogin() {
        System.out.print("Enter your login ID: ");
        Scanner scanner = new Scanner(System.in);
        String loginID = scanner.nextLine().trim();
        UserCredentials credentials = staffCredentials.get(loginID);
        
        if (credentials != null) {
            // Prompt staff members to log in
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (LoginController.login(staffCredentials, loginID, password, scanner)) {
                System.out.println("Login successful. Welcome, " + loginID + "!");
                // Check if password needs to be reset
                if (credentials.getNeedsPasswordReset()) {
                    System.out.println("Would you like to change your password now? (yes/no)");
                    String response = scanner.nextLine().trim();
                    if ("yes".equalsIgnoreCase(response)) {
                        LoginController.promptPasswordChange(scanner, loginID, staffCredentials);
                    }
                }
                // Proceed to the ordering system
            } else {
                System.out.println("Login failed. Incorrect login ID or password.");
            }
        } else {
            System.out.println("Staff member not found. Please try again.");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
        StaffLoginPage staff = new StaffLoginPage();
        staff.StaffLogin();
        // Scanner scanner = new Scanner(System.in);
        // viewer.filterStaff(scanner);
    }
}