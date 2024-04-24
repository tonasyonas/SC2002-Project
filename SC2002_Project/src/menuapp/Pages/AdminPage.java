package menuapp.Pages;

import java.util.Map;
import java.util.Scanner;
import FOMS.account_manager.*;
import FOMS.branch_manager.OpenCloseBranchPage;
import FOMS.order_manager.AddRemovePayment;
import FOMS.Pages.IPage;

/**
 * The {@code AdminPage} class implements the {@code IPage} interface and represents the administration management
 * console within the application. This page allows administrators to perform a variety of staff and branch management
 * operations such as viewing, adding, removing, and transferring staff, promoting staff, managing branches, and
 * modifying payment methods.
 *
 * <p>It encapsulates functionality through a menu-driven approach where administrators can select different options
 * to manage various aspects of the application's administrative tasks. The class handles user inputs and redirects
 * to appropriate pages or functions based on the selected options.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class AdminPage implements IPage {

    /**
     * Starts the administration page, displaying a menu with various management options and processing user input
     * to navigate through different administrative functions.
     */

    @Override
    public void startPage() {
        display();
        int choice;

        String filename = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials(filename);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Choose an option:");
            System.out.println("0. Exit");
            System.out.println("1. View Full Staff List");
            System.out.println("2. View Filtered Staff List");
            System.out.println("3. Add Staff");
            System.out.println("4. Remove Staff");
            System.out.println("5. Promote Staff");
            System.out.println("6. Transfer Staff");
            System.out.println("7. Open/Close Branch");
            System.out.println("8. Add/Remove Payment Method");
            System.out.print("Enter your choice: ");
            // Check if the input is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1/2/3).");
                sc.next(); // Consume invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Full Staff List:");
                    ViewStaffList viewStaffList = new ViewStaffList();
                    viewStaffList.displayAllStaff();
                    break;
                case 2:
                    System.out.println("Filtered Staff List:");

                    ViewFilteredStaffList viewer = new ViewFilteredStaffList(credentialsMap);
                    viewer.filterStaff(sc);
                    break;
                case 3:
                    // Add staff
                    AddStaffPage addStaffPage = new AddStaffPage(sc, credentialsMap, filename);
                    addStaffPage.startPage();
                    break;
                case 4:
                    // Remove staff
                    RemoveStaffPage removeStaffPage = new RemoveStaffPage(sc, credentialsMap, filename);
                    removeStaffPage.startPage();
                    break;
                case 5:
                    PromoteStaff promoteStaff = new PromoteStaff(sc);
                    // Pass the filename and credentials map to EditStaffList method
                    promoteStaff.EditStaffList(filename, credentialsMap);
                    break;
                case 6:
                    TransferStaffPage transferStaffPage = new TransferStaffPage(sc, credentialsMap, filename);
                    transferStaffPage.startPage();
                    break;
                case 7:
                    // New case for opening or closing branches
                    OpenCloseBranchPage.openclosebranch(sc);
                    break;
                case 8:
                    // For adding/removing payment methods
                    AddRemovePayment.addRemovePayment(sc);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);

    }

    /**
     * Displays the initial greeting and menu options to the admin user.
     */
    @Override
    public void display() {
        System.out.println("Welcome, Admin!");
    }

    /**
     * Captures and returns the user's input in a normalized format.
     * 
     * @return The trimmed and lower-cased user input string.
     */

    @Override
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        String userType = sc.nextLine().trim().toLowerCase();
        return userType;
    }

}