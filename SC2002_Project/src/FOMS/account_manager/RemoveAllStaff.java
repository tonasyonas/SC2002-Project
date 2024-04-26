package FOMS.account_manager;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The RemoveAllStaff class provides functionality to remove all staff members from a specified branch.
 * It extends the ABaseRemoveStaff abstract class and implements the logic to filter and remove staff members.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class RemoveAllStaff extends ABaseRemoveStaff {

    String branch;

    /**
     * Constructs a new RemoveAllStaff object with the specified scanner, first flag, and branch.
     * 
     * @param scanner The Scanner object for user input.
     * @param first   A boolean flag indicating if it's the first operation.
     * @param branch  The branch from which to remove all staff members.
     */
    public RemoveAllStaff(Scanner scanner, Boolean first, String branch) {
        super(scanner, first);
        this.branch = branch;
    }

    /**
     * Removes all staff members from the specified branch.
     * 
     * @param filename    The name of the file to write updated credentials.
     * @param credentials A map containing the login IDs as keys and the corresponding UserCredentials objects as values.
     */
    @Override
    public void removeRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        IStaffFilter filter = new BranchFilter(branch);
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());
        List<UserCredentials> filteredList = filter.filter(credentialsList);
    
        if (!filteredList.isEmpty()) {
            Map<String, UserCredentials> updatedCredentials = new HashMap<>();
    
            for (Map.Entry<String, UserCredentials> entry : credentials.entrySet()) {
                String loginID = entry.getKey();
                UserCredentials credential = entry.getValue();
    
                if (!filteredList.contains(credential)) {
                    updatedCredentials.put(loginID, credential);
                }
            }
    
            writeToFile(filename, updatedCredentials);
            System.out.println("Staff members in branch " + branch + " have been removed from credentials.");
        } else {
            System.out.println("No staff members found in branch " + branch + ".");
        }
    }
    
}
