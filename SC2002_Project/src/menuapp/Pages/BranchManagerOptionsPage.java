package menuapp.Pages;

import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.process_manager.BranchStaffPage;
import FOMS.FOMS_entity.*;

/**
 * The {@code BranchManagerOptionsPage} class implements the {@code IPage} interface and serves as the main interface
 * for branch managers within the application. This class facilitates navigation between different management options
 * specific to branch managers, allowing them to access manager-specific and staff-specific functionalities.
 *
 * <p>The class manages user interactions through a menu system where branch managers can choose to navigate to further
 * detailed management pages or perform actions related to branch operations. This includes accessing separate pages
 * for more detailed manager options and staff management within their branch.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class BranchManagerOptionsPage implements IPage {
    private BranchManager branchManager;

    /**
     * Constructs a {@code BranchManagerOptionsPage} with a specified {@code BranchManager}.
     * 
     * @param branchManager The {@code BranchManager} instance associated with this page.
     */
    public BranchManagerOptionsPage (BranchManager branchmanager){
        this.branchManager = branchmanager;
    }

    /**
     * Starts the page by displaying options for branch managers and handling their choices.
     * It provides a looped menu system that continues until the user selects the exit option.
     */

    @Override
    public void startPage() {
        display();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, manager");
        String choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Manager Options");
            System.out.println("2. Staff Options");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Manager Options selected.");
                    ManagerOptionsPage managerOptionsPage = new ManagerOptionsPage(branchManager);
                    managerOptionsPage.startPage();
                    break;
                case "2":
                    System.out.println("Staff Options selected.");
                    BranchStaffPage.startBranchStaffPage(branchManager.getBranch()); 

                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!choice.equals("0"));
        scanner.close();
    }

    /**
     * Displays the initial welcome message and menu options to the branch manager.
     */
    @Override
    public void display() {
        System.out.println("Welcome to Manager Options!");
    }

    /**
     * Retrieves user input and returns it in a normalized format.
     * 
     * @return The trimmed and lower-cased user input string.
     */
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.trim().toLowerCase();
    }

     /**
     * Returns the {@code BranchManager} associated with this page.
     * 
     * @return The {@code BranchManager} object.
     */
    public BranchManager getBranchManager(){
        return branchManager;
    }
}