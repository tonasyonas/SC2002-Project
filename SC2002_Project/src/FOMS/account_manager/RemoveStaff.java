package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import FOMS.branch_manager.*;

/**
 * The RemoveStaff class provides functionality to remove a staff member from the system.
 * It extends the ABaseRemoveStaff abstract class and implements the logic to remove a staff member and handle branch quota ratios.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class RemoveStaff extends ABaseRemoveStaff {

    private BranchQuotaManager branchQuotaManager;
    /**
     * Constructs a new RemoveStaff object with the specified scanner and first flag.
     * 
     * @param scanner The Scanner object for user input.
     * @param first   A boolean flag indicating if it's the first operation.
     */
    public RemoveStaff(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    /**
     * Removes a staff member from the system and handles branch quota ratios.
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
            if (user.getRole().equals("S")) {
                if (first == false) credentials.remove(loginIDToRemove);
                else if ((totalStaff == 7 || totalStaff == 12)){  
                    System.out.println("Does not meet quota ratio! Choose your next action:");
                    System.out.println("1. Add a Staff Member");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            AddStaff addStaff = new AddStaff(scanner, false, branchQuotaManager);
                            addStaff.addSpecificRoleStaff(filename, credentials);
                            credentials.remove(loginIDToRemove);
                            break;
                        case 2:
                            System.out.println("Exiting...");
                            return;  
                        default:
                            System.out.println("Invalid choice. Please enter 1 or 2.");
                            break;
                    }
                }
            
            } else {
                credentials.remove(loginIDToRemove);
                System.out.println("Staff member removed successfully.");
            }
            writeToFile(filename, credentials);
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }
    }
}
