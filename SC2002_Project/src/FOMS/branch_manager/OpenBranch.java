package FOMS.branch_manager;

import java.util.List;
import java.util.Scanner;

/**
 * The OpenBranch class is responsible for opening a new branch and saving its details.
 * It extends the AOpenCloseBranch abstract class.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class OpenBranch extends AOpenCloseBranch {

    /**
     * Opens a new branch by collecting details from the user and saving them.
     * 
     * @param scanner The scanner object to collect user input.
     */
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

    /**
     * Edits the status of a branch by adding its details to the branch list file.
     * 
     * @param branchID The ID of the branch to be opened.
     * @param branchName The name of the branch to be opened.
     * @param quota The quota associated with the branch.
     */
    @Override
    public void editOpenclose(String branchID, String branchName, int quota) {
        List<String> branchList = readBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");

        String newBranchEntry = branchID + ";" + branchName + ";" + quota;
        branchList.add(newBranchEntry);

        writeToTextFile("SC2002_Project/src/FOMS/branch_manager/branch_list.txt", branchList);
    }
}