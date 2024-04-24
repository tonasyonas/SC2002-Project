package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;


public class ConcreteTransferStaff extends AStaffTransfer{
    public ConcreteTransferStaff(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void transfer(String filename, Map<String, UserCredentials> credentials) {
        System.out.println("Enter login ID of the staff member to transfer:");
        String loginIDToTransfer = scanner.nextLine();

        if (credentials.containsKey(loginIDToTransfer)) {
            UserCredentials user = credentials.get(loginIDToTransfer);
            System.out.println("Enter branch for staff member to transfer:");
            String transferredBranch = scanner.next();
            scanner.nextLine();  // Consume the newline

            if (!user.getBranch().equals(transferredBranch)) {
                user.setBranch(transferredBranch);
                System.out.println("Staff member transferred to " + transferredBranch);
            } else {
                System.out.println("Staff member already in " + transferredBranch);
            }
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }

        writeToFile(filename, credentials);
    }

}
