package FOMS.account_manager;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class RemoveAllStaff extends ABaseRemoveStaff {

    String branch;

    public RemoveAllStaff(Scanner scanner, Boolean first, String branch) {
        super(scanner, first);
        this.branch = branch;
    }

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
