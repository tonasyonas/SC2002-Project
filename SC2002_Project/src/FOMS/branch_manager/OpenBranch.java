package FOMS.branch_manager;
import java.util.Scanner;


public class OpenBranch extends AOpenCloseBranch {

    public static void main(String[] args) {
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
        // Call the writeToTextFile method from the abstract class
        writeToTextFile("SC2002_Project/src/FOMS/branch_manager/branch_list.txt", branchID, branchName, quota);
    }
}
