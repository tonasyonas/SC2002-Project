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
        System.out.println("Enter login ID of the staff member to promote:");
        String loginIDToPromote = scanner.nextLine();
        

        // Check if the login ID exists in the credentials map
        if (credentials.containsKey(loginIDToPromote)) {
            // Get the user credentials
            UserCredentials user = credentials.get(loginIDToPromote);
    
            
            // Check if the current role is 'S' (Staff)
            if (user.getRole().equals("S")) {
                // Promote the staff member by changing their role to 'M' (Manager)
                
    
                System.out.println("Does not meet quota ratio! Removing a manager...");
                Scanner removeStaffScanner = new Scanner(System.in);
                RemoveStaff removestaff = new RemoveStaff(removeStaffScanner, false);
                    removestaff.removeRoleStaff(filename, credentials);
                    System.out.println("Staff member promoted successfully.");
                    user.setRole("M");
                
   
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
