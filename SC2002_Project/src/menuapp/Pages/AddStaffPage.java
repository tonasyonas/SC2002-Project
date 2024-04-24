package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

/**
 * The {@code AddStaffPage} class represents a page in the application where users can add new staff members.
 * This page allows users to add staff members with different roles, such as Manager, Regular Staff, or Admin.
 * It implements the {@code IPage} interface to provide a consistent user experience across different pages.
 * 
 * <p>This class handles user interactions, including displaying options, capturing user input, and delegating
 * the staff addition process to specific methods based on the user's choice. It utilizes instances of other classes
 * like {@code AddManager}, {@code AddStaff}, and {@code AddAdmin} to handle the specifics of adding different types
 * of staff members.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class AddStaffPage implements IPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    /**
     * Constructs an {@code AddStaffPage} with the necessary dependencies.
     *
     * @param scanner The {@code Scanner} object to read user input.
     * @param credentialsMap A map holding username as key and {@code UserCredentials} as value for staff management.
     * @param filename The filename where staff credentials are stored.
     */
    public AddStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    /**
     * Starts the page by displaying the available options and processing user actions.
     */
    @Override
    public void startPage() {
        display();
        addStaffBasedOnChoice();
        goToOptionsPage();
    }

    /**
     * Displays the options available on this page to add different types of staff or return to the previous menu.
     */
    @Override
    public void display() {
        System.out.println("Add Staff:");
        System.out.println("1 - Add Manager");
        System.out.println("2 - Add Staff");
        System.out.println("3 - Add Admin");
        System.out.println("4 - Go Back to Options");
    }

     /**
     * Processes the user input to determine the choice of action for adding staff members.
     * Redirects to specific methods to handle each type of staff addition based on the user's choice.
     */
    public void addStaffBasedOnChoice() {
        String choice = getInput();
        switch (choice) {
            case "1":
                addManager();
                break;
            case "2":
                addRegularStaff();
                break;
            case "3":
                addAdmin();
            case "4":
                System.out.println("Returning to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                addStaffBasedOnChoice();
        }
    }
    
    /**
     * Retrieves user input from the console.
     * 
     * @return A trimmed string representing the user's input.
     */
    public String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Initiates the process to add a new manager.
     */
    private void addManager() {
        AddManager addManager = new AddManager(scanner, true);
        addManager.addSpecificRoleStaff(filename, credentialsMap);
    }

    /**
     * Initiates the process to add a new regular staff member.
     */ 
    private void addRegularStaff() {
        AddStaff addStaff = new AddStaff(scanner, true);
        addStaff.addSpecificRoleStaff(filename, credentialsMap);
    }

    /**
     * Initiates the process to add a new admin.
     */
    private void addAdmin() {
        AddAdmin addAdmin = new AddAdmin(scanner, true);
        addAdmin.addSpecificRoleStaff(filename, credentialsMap);
    }

    /**
     * Transitions to the options management page after completing actions on this page.
     */
    private void goToOptionsPage() {
        IPage optionsPage = new AdminPage();
        optionsPage.startPage();
    }
}
