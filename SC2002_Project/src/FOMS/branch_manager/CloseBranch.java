package FOMS.branch_manager;

import java.util.List;
import java.util.Scanner;
import FOMS.account_manager.*;
import java.util.Map; 

public class CloseBranch extends AOpenCloseBranch {

    public static void ClosingBranch(Scanner scanner) {
        System.out.println("Please enter the ID of the branch you want to close:");

        System.out.print("Branch ID: ");
        String branchID = scanner.nextLine();

        String filename = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials(filename);
        RemoveAllStaff removeAllStaff = new RemoveAllStaff(scanner, false, branchID);
        removeAllStaff.removeRoleStaff(filename, credentialsMap);

        CloseBranch branchManager = new CloseBranch();
        branchManager.editOpenclose(branchID, null, 0); 

        System.out.println("Branch has been closed successfully.");
    }

    @Override
    public void editOpenclose(String branchID, String branchName, int quota) {
        List<String> branchList = readBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");

        branchList.removeIf(entry -> entry.startsWith(branchID + ";"));

        writeToTextFile("SC2002_Project/src/FOMS/branch_manager/branch_list.txt", branchList);
    }
}
