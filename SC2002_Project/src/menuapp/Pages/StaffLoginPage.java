package menuapp.Pages;

import FOMS.account_manager.*;
import FOMS.branch_manager.*;
import FOMS.process_manager.*;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;
import FOMS.Pages.IPage;

public class StaffLoginPage implements IPage {

    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private LoginController loginController;
    private Map<String, UserCredentials> staffCredentials;

    public StaffLoginPage() {
        if (!InitializationHandler.isInitialized()) { 
            InitializationHandler handler = new InitializationHandler();
            handler.initializeStaffPasswords();
            InitializationHandler.setInitialized();
        }
        // Now that initialization is done, retrieve the staff credentials
        this.staffCredentials = ReadStaffList.getStaffCredentials(FILE_NAME);
        this.loginController = new LoginController(new SimplePasswordManager());
    }

    @Override
    public void startPage() {
        display();
        String loginID = getInput();
        UserCredentials credentials = staffCredentials.get(loginID);

        Scanner scanner = new Scanner(System.in);
        if (credentials != null) {
            // Prompt staff members to log in
            String password;
            do {
                System.out.print("Enter your password: ");
                password = scanner.nextLine();
                if (loginController.login(loginID, password, scanner)) {
                    System.out.println("Login successful. Welcome, " + loginID + "!");
                        System.out.println("Would you like to change your password now? (yes/no)");
                        String response = scanner.nextLine().trim();
                        if ("yes".equalsIgnoreCase(response)) {
                            loginController.promptPasswordChange(scanner, loginID); 
                        }
                    break; // Exit the loop after successful login
                } else {
                    System.out.println("Login failed. Incorrect login ID or password.");
                }
            } while (true); // Infinite loop until successful login

            // Switch case to create objects based on role after successful login
            switch (credentials.getRole()) {
                case "A":
                    Admin admin = new Admin(credentials.getName(), loginID, credentials.getGender(), credentials.getAge(), credentials.getSalt(), credentials.getHashedPassword(), credentials.getNeedsPasswordReset());
                    AdminPage adminpage = new AdminPage();
                    adminpage.startPage();
                    break;
                case "S":
                    BranchStaff bstaff = new BranchStaff(credentials.getName(), loginID, credentials.getGender(), credentials.getAge(), credentials.getBranch(), credentials.getSalt(), credentials.getHashedPassword(), credentials.getNeedsPasswordReset());
                    // Proceed with branch staff actions
                    BranchStaffPage.startBranchStaffPage(credentials.getBranch()); // Modified call
                    break;
                case "M":
                    BranchManager bmanagement = new BranchManager(credentials.getName(), loginID, credentials.getGender(), credentials.getAge(), credentials.getBranch(), credentials.getSalt(), credentials.getHashedPassword(), credentials.getNeedsPasswordReset());
                    BranchManagerOptionsPage bmoptionspage = new BranchManagerOptionsPage(bmanagement);
                    bmoptionspage.startPage();
                    // Proceed with branch manager actions
                    break;
                default:
                    System.out.println("Invalid role.");
                    break;
            }
        } else {
            System.out.println("Staff member not found. Please try again.");
        }
        scanner.close();
    }

    @Override
    public void display() {
        System.out.println("Welcome to the staff login system!");
        System.out.print("Enter your login ID: ");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine();
        return userType;
    }

}
