package FOMS.branch_manager;

import java.util.List;
import java.util.Scanner;


public class OpenBranch extends AOpenCloseBranch {

    public static void OpeningBranch(Scanner scanner) {
        
        System.out.println("Please enter the details of the branch:");

        System.out.print("Branch ID: ");
        String branchID = scanner.nextLine();

        System.out.print("Branch Name: ");
        String branchName = scanner.nextLine();

        System.out.print("Quota: ");
        int quota = scanner.nextInt();

        OpenBranch branchManager = new OpenBranch();
        branchManager.editOpenclose(branchID, branchName, quota);

        System.out.println("Branch details have been saved successfully.");
    }

    public void editOpenclose(String branchID, String branchName, int quota) {
        List<String> branchList = readBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");

        String newBranchEntry = branchID + ";" + branchName + ";" + quota;
        branchList.add(newBranchEntry);

        writeToTextFile("SC2002_Project/src/FOMS/branch_manager/branch_list.txt", branchList);
    }
}