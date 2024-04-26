package FOMS.account_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Concrete implementation for transferring managers in the system.
 * 
 * This class extends the AStaffTransfer abstract class and provides a concrete implementation for transferring
 * managers from one branch to another. It ensures that branch quota constraints are met during the transfer process.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ConcreteTransferManager extends AStaffTransfer {
    /**
     * Constructs a ConcreteTransferManager object with the specified Scanner.
     * 
     * @param scanner The Scanner object used for user input.
     */
    public ConcreteTransferManager(Scanner scanner) {
        super(scanner);
    }

    /**
     * Transfers a manager from one branch to another.
     * 
     * @param filename The name of the file to write the updated credentials to.
     * @param credentials A map containing the current credentials in the system.
     */
    @Override
    public void transfer(String filename, Map<String, UserCredentials> credentials) {
        boolean first = false;
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());

        System.out.println("Enter login ID of the manager to transfer:");
        String loginIDToTransfer = scanner.nextLine();

        if (credentials.containsKey(loginIDToTransfer)) {
            UserCredentials user = credentials.get(loginIDToTransfer);
            String originalBranch = user.getBranch();

            if (user.getRole().equals("M")) {
                System.out.println("Enter new branchID for manager:");
                String transferredBranch = scanner.next();
                scanner.nextLine();  

                IStaffFilter branchFilter = new BranchFilter(transferredBranch);
                List<UserCredentials> filteredList = branchFilter.filter(credentialsList);
                int totalStaffInTransferredBranch = ViewFilteredStaffList.displayStaff(filteredList);

                if (!user.getBranch().equals(transferredBranch)) {
                    if (totalStaffInTransferredBranch == 5 || totalStaffInTransferredBranch == 10) {
                        System.out.println("Transferred branch does not meet quota ratio!");
                        System.out.println("1. Remove a Manager from transferred branch");
                        System.out.println("2. Add Staff to transferred branch");
                        System.out.print("Enter choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                RemoveManager removeManager = new RemoveManager(scanner, false);
                                removeManager.removeRoleStaff(filename, credentials);
                                first = true;
                                break;
                            case 2:
                                AddStaff addStaff = new AddStaff(scanner, false);
                                addStaff.addSpecificRoleStaff(filename, credentials);
                                first = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter 1 or 2.");
                                break;
                        }
                    } else {
                        System.out.println("Transfer denied due to transferred branch constraint");
                    }

                    if (first == true) {
                        System.out.printf("%s does not hit quota ratio\n", originalBranch);
                        System.out.printf("Add manager to %s\n", originalBranch);
                        AddManager addManager = new AddManager(scanner, false);
                        addManager.addSpecificRoleStaff(filename, credentials);
                        user.setBranch(transferredBranch);
                             
                    }
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
