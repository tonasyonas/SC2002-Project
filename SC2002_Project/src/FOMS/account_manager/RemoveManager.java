package FOMS.account_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import FOMS.branch_manager.*;

/**
 * The RemoveManager class provides functionality to remove a manager from the system.
 * It extends the ABaseRemoveStaff abstract class and implements the logic to remove a manager and handle branch quota ratios.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class RemoveManager extends ABaseRemoveStaff {

    private BranchQuotaManager branchQuotaManager;
    /**
     * Constructs a new RemoveManager object with the specified scanner and first flag.
     * 
     * @param scanner The Scanner object for user input.
     * @param first   A boolean flag indicating if it's the first operation.
     */
    public RemoveManager(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    /**
     * Removes a manager from the system and handles branch quota ratios.
     * 
     * @param filename    The name of the file to write updated credentials.
     * @param credentials A map containing the login IDs as keys and the corresponding UserCredentials objects as values.
     */
    @Override
    public void removeRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());

        System.out.println("Enter login ID of the staff member to remove:");
        String loginIDToRemove = scanner.nextLine().trim();

        if (credentials.containsKey(loginIDToRemove)) {
            UserCredentials user = credentials.get(loginIDToRemove);
            IStaffFilter filter = new BranchFilter(user.getBranch());
            List <UserCredentials> filteredList = filter.filter(credentialsList);
            int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);
            if (user.getRole().equals("M")) {
                if (first == false) credentials.remove(loginIDToRemove);

                else if ((totalStaff == 7 || totalStaff == 12)){  
                    System.out.println("Does not meet qutoa ratio! Add a manager");
                    System.out.println("1. Add a Manager");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            AddManager addManager = new AddManager(scanner, false, branchQuotaManager);
                            addManager.addSpecificRoleStaff(filename, credentials);
                            credentials.remove(loginIDToRemove);

                            break;
                        case 2:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter 1 or 2.");
                            break;
                    }
                }
                else {
                    System.out.println("Does not meet quota ratio! Please try again.");
                }
            }
            else {
                System.out.println("The specified ID does not belong to a manager.");
            }
            writeToFile(filename, credentials);
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }    
    }
}
