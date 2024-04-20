package FOMS.account_manager;



import java.util.Map;
import java.util.Scanner;

public class TransferStaff extends AStaffListEditor {
    private Scanner scanner;

    public TransferStaff(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
        // Assuming the user provides the login ID of the staff member to promote
        System.out.println("Enter login ID of the staff member to transfer:");
        String loginIDToTransfer = scanner.nextLine();

        // Check if the login ID exists in the credentials map
        if (credentials.containsKey(loginIDToTransfer)) {
            // Get the user credentials
            UserCredentials user = credentials.get(loginIDToTransfer);
            System.out.println("Enter branch for staff member to transfer:");
            String transferredBranch = scanner.next();
            
            // Check if the current role is 'S' (Staff)
            if (user.getBranch().equals(transferredBranch)) {
                // Promote the staff member by changing their role to 'M' (Manager)
                System.out.println("Staff member already in Branch.");
   
            } else {
                user.setBranch(transferredBranch);
                System.out.println("Staff member transferred");
            }
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }

        // Write the updated credentials back to the file
        writeToFile(filename, credentials);
    }
}

