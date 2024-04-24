package FOMS.account_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PromoteStaff extends AStaffListEditor {
    private Scanner scanner;
    public PromoteStaff(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
        // Assuming the user provides the login ID of the staff member to promote
         List<UserCredentials> credentialsList= new ArrayList<>(credentials.values());
        System.out.println("Enter login ID of the staff member to promote:");
        String loginIDToPromote = scanner.nextLine();
        

        // Check if the login ID exists in the credentials map
        if (credentials.containsKey(loginIDToPromote)) {
            // Get the user credentials
            UserCredentials user = credentials.get(loginIDToPromote);
            IStaffFilter filter = new BranchFilter(user.getBranch());
            List <UserCredentials> filteredList = filter.filter(credentialsList);
            int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);
    

            
            // Check if the current role is 'S' (Staff)
            if (user.getRole().equals("S")) {
                // Promote the staff member by changing their role to 'M' (Manager)
                if (totalStaff == 5 || totalStaff == 10) {
    
                    // switch case to choose to add manager or remove staff or exit
                    System.out.println("Does not meet quota ratio! Adding a manager...");
                    Scanner addManagerScanner = new Scanner(System.in);
                    AddManager addManager = new AddManager(addManagerScanner, false);
                    addManager.addSpecificRoleStaff(filename, credentials);
        
        
                }
                else {
                    System.out.println("Staff member promoted successfully.");
                    user.setRole("M");
                }
   
            } else {
                System.out.println("Staff member is already an Admin or Manager.");
            }
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }

        // Write the updated credentials back to the file
        writeToFile(filename, credentials);
    }
}
