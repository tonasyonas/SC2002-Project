package FOMS.account_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
// import java.io.FileWriter;

public class RemoveStaffList extends AStaffListEditor {
    private Scanner scanner;
    Boolean first = true;

    public RemoveStaffList (Scanner scanner, Boolean first){
        this.scanner = scanner;
        this.first = first;
    }

    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
        // Assuming the user provides the login ID of the staff member to remove
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());
        System.out.println("Enter login ID of the staff member to remove:");
        String loginIDToRemove = scanner.nextLine();

        // Check if the login ID exists in the credentials map
        if (credentials.containsKey(loginIDToRemove)) {
            UserCredentials user = credentials.get(loginIDToRemove);
            // Remove the staff member with the specified login ID
            IStaffFilter filter = new BranchFilter(user.getBranch());
            List <UserCredentials> filteredList = filter.filter(credentialsList);
            int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);

            if (user.getRole().equals("S") && (first == true)){
                 if (totalStaff == 7 || totalStaff == 12){
                    System.out.println("Does not meet qutoa ratio! Add a staff");
                    Scanner addStaffScanner = new Scanner(System.in);
                    AddStaffList addStaffList = new AddStaffList(addStaffScanner, false);
                    addStaffList.EditStaffList(filename, credentials);
                }
            }

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

        // Write the updated credentials back to the file
    }
}
