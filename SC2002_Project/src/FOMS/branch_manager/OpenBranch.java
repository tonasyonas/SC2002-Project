package FOMS.branch_manager;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class OpenBranch extends AOpenCloseBranch {

    public static void OpeningBranch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the details of the branch:");

        System.out.print("Branch ID: ");
        String branchID = scanner.nextLine();

        System.out.print("Branch Name: ");
        String branchName = scanner.nextLine();

        System.out.print("Quota: ");
        int quota = scanner.nextInt();

        // Create an instance of OpenBranch to perform the operation
        OpenBranch branchManager = new OpenBranch();
        branchManager.editOpenclose(branchID, branchName, quota);

        System.out.println("Branch details have been saved successfully.");
        scanner.close();
    }

    // Override the abstract method from AOpenCloseBranch
    public void editOpenclose(String branchID, String branchName, int quota) {
        // Read existing branch list
        List<String> branchList = readBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");

        // Append the new branch entry
        String newBranchEntry = branchID + ";" + branchName + ";" + quota;
        branchList.add(newBranchEntry);

        // Write the updated branch list to the text file
        writeToTextFile("SC2002_Project/src/FOMS/branch_manager/branch_list.txt", branchList);
    }
}