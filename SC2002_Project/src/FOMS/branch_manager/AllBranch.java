package FOMS.branch_manager;

import java.util.List;

public class AllBranch {
    public static String[] getBranchIDs(List<Branch> branchList) {
        // Extract branch IDs and store them in a String array
        String[] branchIDs = new String[branchList.size()];
        for (int i = 0; i < branchList.size(); i++) {
            branchIDs[i] = branchList.get(i).getBranchID();
        }
        
        return branchIDs;
    }
}
