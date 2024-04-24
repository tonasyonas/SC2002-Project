package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;


public class ConcreteTransferStaff extends AStaffTransfer{
    public ConcreteTransferStaff(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void transfer(String filename, Map<String, UserCredentials> credentials) {
        System.out.println("Enter login ID of the manager to transfer:");
        String loginIDToTransfer = scanner.nextLine();

        if (credentials.containsKey(loginIDToTransfer)) {
            UserCredentials user = credentials.get(loginIDToTransfer);
            if (user.getRole().equals("M")) {
                System.out.println("Enter new branch for manager:");
                String transferredBranch = scanner.next();
                scanner.nextLine();  // Consume the newline

                if (!user.getBranch().equals(transferredBranch)) {
                    user.setBranch(transferredBranch);
                    System.out.println("Manager transferred to " + transferredBranch);
                } else {
                    System.out.println("Manager already in " + transferredBranch);
                }
            } else {
                System.out.println("Specified ID does not belong to a manager.");
            }
        } else {
            System.out.println("Manager with the provided login ID does not exist.");
        }

        writeToFile(filename, credentials);
    }

}
