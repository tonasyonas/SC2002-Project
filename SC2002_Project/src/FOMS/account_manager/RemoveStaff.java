package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class RemoveStaff extends ABaseRemoveStaff {

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

            if (user.getRole().equals("S") && first == true) {
                if (totalStaff == 7 || totalStaff == 12){
                    System.out.println("Does not meet qutoa ratio! Add a staff");
                    Scanner addStaffScanner = new Scanner(System.in);
                    AddStaffList addStaffList = new AddStaffList(addStaffScanner, false);
                    addStaffList.EditStaffList(filename, credentials);
                }
            }
            credentials.remove(loginIDToRemove);
            writeToFile(filename, credentials);
            System.out.println("Staff member removed successfully.");
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }


    }
}
