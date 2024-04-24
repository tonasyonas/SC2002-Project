package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;

public class RemoveManager extends ABaseRemoveStaff {

    public RemoveManager(Scanner scanner,    Boolean first) {
        super(scanner, first);
    }

    @Override
    public void removeSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        System.out.println("Enter login ID of the staff member to remove:");
        String loginIDToRemove = scanner.nextLine().trim();

        if (credentials.containsKey(loginIDToRemove)) {
            UserCredentials user = credentials.get(loginIDToRemove);
            if (user.getRole().equals("M")&& (first == true)){
                System.out.println("Does not meet qutoa ratio! Remove a manager");
                Scanner addStaffScanner = new Scanner(System.in);
                AddStaffList addStaffList = new AddStaffList(addStaffScanner, false);
                addStaffList.EditStaffList(filename, credentials);
            }
            credentials.remove(loginIDToRemove);
            writeToFile(filename, credentials);
            System.out.println("Staff member removed successfully.");
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }
    }

}
