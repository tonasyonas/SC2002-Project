package menuapp.Pages;

import FOMS.account_manager.*;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;

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
            String password;
            do {
                System.out.print("Enter your password: ");
                password = scanner.nextLine();
                if (LoginController.login(staffCredentials, loginID, password, scanner)) {
                    System.out.println("Login successful. Welcome, " + loginID + "!");
                    // Check if password needs to be reset
                    if (credentials.getNeedsPasswordReset()) {
                        System.out.println("Would you like to change your password now? (yes/no)");
                        String response = scanner.nextLine().trim();
                        if ("yes".equalsIgnoreCase(response)) {
                            LoginController.promptPasswordChange(scanner, loginID, staffCredentials);
                        }
                        switch (credentials.getRole()){
                            case "A": 
                                Admin admin = new Admin(credentials.getName(), loginID, credentials.getGender(), credentials.getAge(), credentials.getSalt(), credentials.getHashedPassword(), credentials.getNeedsPasswordReset());
                            case "S": 
                                BranchStaff bstaff = new BranchStaff(credentials.getName(), loginID, credentials.getGender(), credentials.getAge(), credentials.getBranch(), credentials.getSalt(), credentials.getHashedPassword(), credentials.getNeedsPasswordReset());
                            case "M":
                                BranchManager bmanagement = new BranchManager(credentials.getName(), loginID, credentials.getGender(), credentials.getAge(), credentials.getBranch(), credentials.getSalt(), credentials.getHashedPassword(), credentials.getNeedsPasswordReset());
                        }
                    }
                    break;
                    // Proceed to the ordering system
                } else {
                    System.out.println("Login failed. Incorrect login ID or password.");
                }
        } while (!LoginController.login(staffCredentials, loginID, password, scanner));
        } else {
            System.out.println("Staff member not found. Please try again.");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        StaffLoginPage staff = new StaffLoginPage();
        staff.StaffLogin();
    }
}