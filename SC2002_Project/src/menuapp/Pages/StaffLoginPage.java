package menuapp.Pages;

import FOMS.account_manager.*;
import FOMS.process_manager.*;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;
import FOMS.Pages.IPage;

/**
 * The {@code StaffLoginPage} class implements the {@code IPage} interface and facilitates the staff login process
 * for the application. This class manages the verification of user credentials and directs users to the appropriate
 * system pages based on their roles.
 *
 * <p>This class uses a {@code LoginController} to handle the authentication and optionally allows users to change their
 * passwords upon successful login. It checks the user's role after logging in and navigates to either the {@code AdminPage},
 * {@code BranchStaffPage}, or {@code BranchManagerOptionsPage} depending on the role assigned in the credentials.</p>
 *
 * <p>Upon initialization, the class ensures that all staff passwords are initialized properly if they have not been set up
 * already. This setup is handled by an {@code InitializationHandler}, emphasizing security and proper data management.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class StaffLoginPage implements IPage {

    private static final String FILE_NAME = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private LoginController loginController;
    private Map<String, UserCredentials> staffCredentials;

    /**
     * Constructs the {@code StaffLoginPage} and initializes necessary components for the login process.
     * This includes loading staff credentials and setting up the {@code LoginController}.
     */
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

    /**
     * Initiates the staff login process, guides users through authentication, and directs them to the appropriate
     * page based on their roles. This method provides a loop for continuous password attempts until successful login.
     */
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
                        System.out.print("Would you like to change your password now? (Yes/No) ");
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
                    AdminPage adminpage = new AdminPage();
                    adminpage.startPage();
                    break;
                case "S":
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
            startPage();
        }
        scanner.close();
    }

    /**
     * Displays the initial login prompt to the user asking for their login ID.
     */
    @Override
    public void display() {
        System.out.println("Welcome to the staff login system!");
        System.out.print("Enter your login ID: ");
    }

    /**
     * Retrieves the login ID input from the user.
     *
     * @return The user's login ID as a string.
     */
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine();
        return userType;
    }

}
