package FOMS.account_manager;

import java.util.Map;
import java.util.Scanner;

    /**
     * The PromoteStaff class allows for promoting a staff member to a higher role, such as Manager.
     * It extends the AStaffListEditor abstract class and provides functionality to edit the staff list accordingly.
     * 
     * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
     * @version 1.0
     * @since 2024-04-24
     */
public class PromoteStaff extends AStaffListEditor {
    private Scanner scanner;

    /**
     * Constructs a new PromoteStaff object with the specified Scanner for user input.
     * 
     * @param scanner The Scanner object for user input.
     */
    public PromoteStaff(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Promotes a staff member to a higher role.
     * 
     * @param filename    The name of the file to write the updated credentials to.
     * @param credentials A map containing the current credentials in the system.
     */
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
