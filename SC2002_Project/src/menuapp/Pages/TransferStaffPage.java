package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

/**
 * The {@code TransferStaffPage} class implements the {@code IPage} interface and provides functionality for transferring staff
 * members between different branches or departments within the organization. This class facilitates the transfer of both
 * managers and regular staff members, supporting administrative operations related to staff management.
 *
 * <p>This class uses a console-based interface to interact with the user, allowing them to choose the type of staff member
 * to transfer and then executing the transfer based on user input. It ensures that staff data is updated accordingly
 * in the system's credential storage.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class TransferStaffPage implements IPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    /**
     * Constructs a {@code TransferStaffPage} with the necessary system resources to facilitate staff transfers.
     *
     * @param scanner A {@code Scanner} for capturing user input.
     * @param credentialsMap A {@code Map} of user credentials keyed by user IDs, used for updating staff data.
     * @param filename The filename where staff credentials are stored, allowing updates to be persisted.
     */
    public TransferStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    /**
     * Initiates the staff transfer process, guiding the user through the selection and transfer steps and finally
     * returning to the main options page.
     */
    @Override
    public void startPage() {
        display();
        transferStaffBasedOnChoice();
        goToOptionsPage();
    }

    /**
     * Displays the menu options to the user for selecting the type of staff transfer: transferring a manager,
     * a regular staff member, or returning to the previous menu.
     */
    @Override
    public void display() {
        System.out.println("Transfer Staff:");
        System.out.println("1 - Transfer Manager");
        System.out.println("2 - Transfer Staff");
        System.out.println("3 - Go Back to Options");
    }

    /**
     * Handles the user's choice for transferring staff by prompting further input and calling the appropriate methods
     * to process the transfer based on the type of staff selected.
     */
    public void transferStaffBasedOnChoice() {
        String choice = getInput();
        switch (choice) {
            case "1":
                transferManager();
                break;
            case "2":
                transferRegularStaff();
                break;
            case "3":
                System.out.println("Returning to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                transferStaffBasedOnChoice();
        }
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The trimmed string of user input.
     */
    public String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Executes the transfer process for a manager, utilizing a specialized transfer handler.
     */
    private void transferManager() {
        ConcreteTransferManager transferM = new ConcreteTransferManager(scanner);
        transferM.transfer(filename, credentialsMap);
    }

    /**
     * Executes the transfer process for regular staff members, utilizing a specialized transfer handler.
     */
    private void transferRegularStaff() {
        ConcreteTransferStaff transferS = new ConcreteTransferStaff(scanner);
        transferS.transfer(filename, credentialsMap);
    }

    /**
     * Navigates back to the main administrative options page after completing the staff transfer operations.
     */
    private void goToOptionsPage() {
        IPage optionsPage = new AdminPage();
        optionsPage.startPage();
    }
}
