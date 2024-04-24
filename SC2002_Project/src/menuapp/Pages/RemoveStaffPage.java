package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

/**
 * The {@code RemoveStaffPage} class implements the {@code IPage} interface, providing functionalities
 * to remove staff members from a system. This class allows the removal of different types of staff
 * including managers and regular staff members. It uses a console-based menu system to allow the
 * user to choose which type of staff to remove.
 *
 * This class demonstrates interaction with the user to manage staff removal based on the user's
 * choices through a streamlined text-based interface. It leverages other components to handle
 * the specifics of removing staff credentials.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class RemoveStaffPage implements IPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    /**
     * Constructs a {@code RemoveStaffPage} with necessary components like Scanner, credentials map, and filename.
     *
     * @param scanner A {@code Scanner} to read user input.
     * @param credentialsMap A {@code Map} containing staff credentials for identification and removal.
     * @param filename The path to the file that stores staff credentials.
     */
    public RemoveStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    /**
     * Initiates the staff removal process by displaying options to the user and processing the selected choice.
     */
    @Override
    public void startPage() {
        display();
        removeStaffBasedOnChoice();
        goToOptionsPage();
    }

    /**
     * Displays the initial menu options to the user for staff removal.
     */
    @Override
    public void display() {
        System.out.println("Add Staff:");
        System.out.println("1 - Remove Manager");
        System.out.println("2 - Remove Staff");
        System.out.println("3 - Go Back to Options");
    }

    /**
     * Processes the user's choice for removing specific roles of staff. This method uses recursion to handle invalid choices,
     * ensuring that the user selects a valid option.
     */
    public void removeStaffBasedOnChoice() {
        String choice = getInput();
        switch (choice) {
            case "1":
                removeManager();
                break;
            case "2":
                removeRegularStaff();
                break;
            case "3":
                System.out.println("Returning to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                removeStaffBasedOnChoice();
        }
    }

     /**
     * Captures user input from the console.
     *
     * @return The trimmed string of user input.
     */
    public String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Facilitates the removal of a manager. This method leverages another class designed specifically for removing managers.
     */
    private void removeManager() {
        RemoveManager removeManager = new RemoveManager(scanner, true);
        removeManager.removeSpecificRoleStaff(filename, credentialsMap);
    }

    /**
     * Facilitates the removal of a regular staff member. This method leverages another class designed specifically for removing regular staff.
     */
    private void removeRegularStaff() {
        RemoveStaff removeStaff = new RemoveStaff(scanner, true);
        removeStaff.removeSpecificRoleStaff(filename, credentialsMap);
    }

    /**
     * Navigates back to the options page after performing the staff removal actions.
     */
    private void goToOptionsPage() {
        IPage optionsPage = new AdminPage();
        optionsPage.startPage();
    }
}
