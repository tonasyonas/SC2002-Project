package FOMS.account_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;
/**
 * The AddManager class is responsible for adding a manager to the system.
 * It extends the ABaseAddStaff abstract class and implements the addSpecificRoleStaff method.
 * This class prompts the user to input manager details and adds them to the system.
 */
public class AddManager extends ABaseAddStaff {
    /**
     * The default password used for new manager accounts.
     */
    private static final String DEFAULT_PASSWORD = "password";

    /**
     * Constructs a new AddManager object.
     * @param scanner The Scanner object used for user input.
     * @param first A boolean indicating whether this is the first manager being added.
     */
    public AddManager(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    /**
     * Adds a manager to the system.
     * @param filename The name of the file to write the updated credentials to.
     * @param credentials A map containing the current credentials in the system.
     */
    @Override
    public void addSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        // Assuming the user provides all necessary details for adding a staff member
          List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter login ID:");
        String loginID = scanner.nextLine();

        System.out.println("Enter gender (M/F):");
        String genderStr = scanner.nextLine();
        Staff.Gender gender = genderStr.equalsIgnoreCase("F") ? Staff.Gender.F : Staff.Gender.M;

        System.out.println("Enter age:");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter branch:");
        String branch = scanner.nextLine();

        // Generate salt and hash password
        String salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(DEFAULT_PASSWORD, salt);

        // Creating and adding new staff credentials
        UserCredentials newCredentials = new UserCredentials(name, "M", gender, age, branch, salt, hashedPassword, true);
        credentials.put(loginID, newCredentials);

         IStaffFilter filter = new BranchFilter(branch);
        List <UserCredentials> filteredList = filter.filter(credentialsList);
        int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);

        if (first==true && (totalStaff == 5 || totalStaff == 10)) {
            System.out.println("Does not meet quota ratio!");
            System.out.println("1. Remove a Manager");
            System.out.println("2. Add Staff");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    credentials.put(loginID, newCredentials);
                    RemoveManager removeManager = new RemoveManager(scanner, false);
                    removeManager.removeSpecificRoleStaff(filename, credentials);
                    System.out.println("Manager Added Successfully");

                    break;
                case 2:
                    credentials.put(loginID, newCredentials);
                    AddStaff addStaff = new AddStaff(scanner, false);
                    addStaff.addSpecificRoleStaff(filename, credentials);
                    System.out.println("Manager Added Successfully");

                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    break;
            }

        }

        else if(first == false){
            credentials.put(loginID, newCredentials);
            System.out.println("Manager Added Successfully");



        }
        else {
            System.out.println("Cannot add manager. Please try another option");
        }

        // Write updated credentials to the file
        writeToFile(filename, credentials);

    }
}
