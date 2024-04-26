package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import FOMS.branch_manager.*;


public class RemoveStaff extends ABaseRemoveStaff {

    private BranchQuotaManager branchQuotaManager;
    public RemoveStaff(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    @Override
    public void removeSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());
        System.out.println("Enter login ID of the staff member to remove:");
        String loginIDToRemove = scanner.nextLine().trim();

        if (credentials.containsKey(loginIDToRemove)) {
            UserCredentials user = credentials.get(loginIDToRemove);
            IStaffFilter filter = new BranchFilter(user.getBranch());
            List <UserCredentials> filteredList = filter.filter(credentialsList);
            int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);
            if (user.getRole().equals("S")) {
                if (first == false) credentials.remove(loginIDToRemove);
                else if ((totalStaff == 7 || totalStaff == 12)){  
                    System.out.println("Does not meet quota ratio! Choose your next action:");
                    System.out.println("1. Add a Staff Member");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (choice) {
                        case 1:
                            AddStaff addStaff = new AddStaff(scanner, false, branchQuotaManager);
                            addStaff.addSpecificRoleStaff(filename, credentials);
                            credentials.remove(loginIDToRemove);
                            break;
                        case 2:
                            System.out.println("Exiting...");
                            return;  // Exit the method
                        default:
                            System.out.println("Invalid choice. Please enter 1 or 2.");
                            break;
                    }
                }
            
            } else {
                System.out.println("Staff member removed successfully.");
            }
            writeToFile(filename, credentials);
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }
    }
}
