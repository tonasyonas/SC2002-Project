package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;

public class RemoveManager extends ABaseRemoveStaff {

    public RemoveManager(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    @Override
    public void removeSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        System.out.println("Enter login ID of the staff member to remove:");
        String loginIDToRemove = scanner.nextLine().trim();

        if (credentials.containsKey(loginIDToRemove)) {
            UserCredentials user = credentials.get(loginIDToRemove);
            if (user.getRole().equals("M")&& (first == true)){
                System.out.println("Does not meet qutoa ratio! Add a manager");
                System.out.println("1. Add a Manager");
                System.out.println("2. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        // Re-use the scanner instead of creating a new one
                        AddManager addManager = new AddManager(scanner, false);
                        addManager.addSpecificRoleStaff(filename, credentials);
                        break;
                    case 2:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                        break;
                }
            }
            else {
                System.out.println("The specified ID does not belong to a manager.");
            }
            // Save changes to the file regardless of actions taken
            writeToFile(filename, credentials);
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }    
    }
}
