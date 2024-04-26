package menuapp.Pages;

import FOMS.account_manager.*;
import FOMS.branch_manager.DisplayManagerBranchStaff;
import FOMS.FOMS_entity.BranchManager;
import FOMS.Pages.IPage;
import java.util.Map;
import java.util.Scanner;

/**
 * The {@code ManagerOptionsPage} class implements the {@code IPage} interface and acts as a controller within the application,
 * facilitating the management of menu items and staff details for branch managers. This class exemplifies several SOLID principles:
 *
 * <ul>
 *   <li><strong>Single Responsibility Principle:</strong> This class is responsible solely for managing user interactions related to manager-specific options, delegating business logic and data access to other classes.</li>
 *   <li><strong>Open/Closed Principle:</strong> It is open for extension but closed for modification; new functionalities like handling additional manager tasks can be added without modifying the existing code structure.</li>
 *   <li><strong>Dependency Inversion Principle:</strong> It depends on abstractions by using the {@code IPage} interface and not concrete implementations directly from higher modules, facilitating looser coupling and easier manageability.</li>
 * </ul>
 *
 * <p>This class manages user inputs and directs the flow of the application based on the selected options, such as adding, editing, or removing menu items, and displaying staff lists, demonstrating adherence to interface segregation by segmenting user actions cleanly.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ManagerOptionsPage implements IPage {

    private BranchManager branchmanager;
    private Map<String, UserCredentials> staffCredentials;

    /**
     * Constructs a {@code ManagerOptionsPage} with a specified {@code BranchManager}.
     * This constructor exemplifies the Dependency Inversion Principle by relying on a high-level {@code BranchManager} module for managerial functions.
     *
     * @param branchManager The {@code BranchManager} instance associated with this page.
     */
    public ManagerOptionsPage(BranchManager branchmanager) {
        this.branchmanager = branchmanager;
        this.staffCredentials = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
    }

    /**
     * Displays the list of staff under the management of the logged-in branch manager, encapsulating the display logic in a separate class to adhere to the Single Responsibility Principle.
     */
    public void displayStaffList() {
        DisplayManagerBranchStaff branchlist = new DisplayManagerBranchStaff(staffCredentials);
        branchlist.displaystaffDetails(branchmanager);
    }
    
    /**
     * Initiates the page for managing menu items and staff, offering options and reacting according to user input. This method governs the flow based on the selected option,
     * demonstrating the application's modularity and maintainability.
     */
    @Override
    public void startPage() {
        display();
        Scanner scanner = new Scanner(System.in);
        String choice;
        int choiceint;
        System.out.println("1. Add Menu Item");
        System.out.println("2. Edit Menu Item");
        System.out.println("3. Remove Menu Item");
        System.out.println("4. Display Staff List");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            try{
                choiceint = Integer.parseInt(choice);
            }
            catch(Exception e){
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            switch (choiceint) {
                case 1:
                    // Create an instance of AddMenuItemPage and start it
                    AddMenuItemPage addMenuItemPage = new AddMenuItemPage(branchmanager);
                    addMenuItemPage.startPage();
                    break;
                case 2:
                    EditMenuItemPage editMenuItemPage = new EditMenuItemPage(branchmanager);
                    editMenuItemPage.startPage();
                    break;
                case 3:
                    RemoveMenuItemPage removeMenuItemPage = new RemoveMenuItemPage(branchmanager);
                    removeMenuItemPage.startPage();
                    break;
                case 4:
                    displayStaffList();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number (0-4).");
                    break;
            }
        } while (!choice.equals("0"));
    }

    /**
     * Displays the initial options menu to the branch manager.
     */
    @Override
    public void display() {
        System.out.println("Manager Options:");
    }

    /**
     * Retrieves and processes user input, trimming whitespace and converting to lowercase to ensure consistent handling.
     *
     * @return The processed user input as a string.
     */
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim().toLowerCase();
        // scanner.close(); // Do not close the scanner here to avoid closing System.in stream prematurely.
        return userInput;
    }

}
