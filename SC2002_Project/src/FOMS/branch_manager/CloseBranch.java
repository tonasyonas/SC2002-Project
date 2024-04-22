package FOMS.branch_manager;

import java.util.List;
import java.util.Scanner;

public class CloseBranch extends AOpenCloseBranch {

    public static void ClosingBranch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the ID of the branch you want to close:");

        System.out.print("Branch ID: ");
        String branchID = scanner.nextLine();

        // Create an instance of CloseBranch to perform the operation
        CloseBranch branchManager = new CloseBranch();
        branchManager.editOpenclose(branchID, null, 0); // Pass null and 0 as placeholders for branchName and quota

        System.out.println("Branch has been closed successfully.");
        scanner.close();
    }

    // Override the editOpenclose method
    @Override
    public void editOpenclose(String branchID, String branchName, int quota) {
        // Read existing branch list
        List<String> branchList = readBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");

        // Remove the branch entry with the given branchID
        branchList.removeIf(entry -> entry.startsWith(branchID + ";"));

        // Write the updated branch list to the text file
        writeToTextFile("SC2002_Project/src/FOMS/branch_manager/branch_list.txt", branchList);
    }
}
