package FOMS.branch_manager;

import java.util.List;

/**
 * The AllBranch class provides methods related to retrieving branch IDs.
 * It contains a static method to retrieve branch IDs from a list of branches.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class AllBranch {
     /**
     * Retrieves branch IDs from a list of branches.
     * 
     * @param branchList The list of branches from which to retrieve IDs.
     * @return An array of branch IDs.
     */
    public static String[] getBranchIDs(List<Branch> branchList) {
        String[] branchIDs = new String[branchList.size()];
        for (int i = 0; i < branchList.size(); i++) {
            branchIDs[i] = branchList.get(i).getBranchID();
        }
        
        return branchIDs;
    }
}
