package FOMS.account_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConcreteTransferStaff extends AStaffTransfer {
    public ConcreteTransferStaff(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void transfer(String filename, Map<String, UserCredentials> credentials) {
        boolean first = false;
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());

        System.out.println("Enter login ID of the staff to transfer:");
        String loginIDToTransfer = scanner.nextLine();

        if (credentials.containsKey(loginIDToTransfer)) {
            UserCredentials user = credentials.get(loginIDToTransfer);
            String originalBranch = user.getBranch();

            if (user.getRole().equals("S")) {
                System.out.println("Enter new branchID for staff:");
                String transferredBranch = scanner.next();
                scanner.nextLine();  

                IStaffFilter branchFilter = new BranchFilter(transferredBranch);
                List<UserCredentials> filteredList = branchFilter.filter(credentialsList);
                int totalStaffInTransferredBranch = ViewFilteredStaffList.displayStaff(filteredList);

                if (!user.getBranch().equals(transferredBranch)) {
                    if (totalStaffInTransferredBranch == 5 || totalStaffInTransferredBranch == 10) {
                        System.out.println("Transferred branch does not meet quota ratio!");
                        System.out.println("1. Remove a Staff from transferred branch");
                        System.out.println("2. Add Manager to transferred branch");
                        System.out.print("Enter choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (choice) {
                            case 1:
                                RemoveStaff removeStaff = new RemoveStaff(scanner, false);
                                removeStaff.removeRoleStaff(filename, credentials);
                                first = true;
                                break;
                            case 2:
                                AddManager addManager = new AddManager(scanner, false);
                                addManager.addSpecificRoleStaff(filename, credentials);
                                first = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter 1 or 2.");
                                break;
                        }
                    } 
                    else {
                        first = true;
                    }
        

                    if (first == true) {
                        System.out.printf("%s does not hit quota ratio\n", originalBranch);
                        System.out.printf("Add Staff to %s\n", originalBranch);
                        AddStaff addStaff = new AddStaff(scanner, false);
                        addStaff.addSpecificRoleStaff(filename, credentials);
                        user.setBranch(transferredBranch);
                             
                    }
                } else {
                    System.out.println("Staff already in " + transferredBranch);
                }
            } else {
                System.out.println("Specified ID does not belong to a staff.");
            }
        } else {
            System.out.println("Staff with the provided login ID does not exist.");
        }

        writeToFile(filename, credentials);
    }
}
