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
        System.out.println("Enter login ID of the staff member to promote:");
        String loginIDToPromote = scanner.nextLine();
        

        if (credentials.containsKey(loginIDToPromote)) {
            UserCredentials user = credentials.get(loginIDToPromote);
    
            
            if (user.getRole().equals("S")) {
                
    
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

        writeToFile(filename, credentials);

    }
    
}
