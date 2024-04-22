package FOMS.branch_manager;


import java.util.Scanner;



import java.util.Scanner;

public class OpenCloseBranchPage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the details of the branch:");

        System.out.print("Branch ID: ");
        String branchID = scanner.nextLine();

        System.out.print("Branch Name: ");
        String branchName = scanner.nextLine();

        System.out.print("Quota: ");
        int quota = scanner.nextInt();

        // Create an instance of AOpenCloseBranch to perform the operation
        AOpenCloseBranch branchManager = new AOpenCloseBranch() {
            @Override
            public void editOpenclose(String branchID, String branchName, int quota) {
                // Call the writeToTextFile method from the abstract class
                writeToTextFile("branches.txt", branchID, branchName, quota);
            }
        };

        // Call the editOpenclose method with the provided inputs
        branchManager.editOpenclose(branchID, branchName, quota);

        System.out.println("Branch details have been saved successfully.");
        scanner.close();
    }
}
