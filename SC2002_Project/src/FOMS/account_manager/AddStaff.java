package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import FOMS.FOMS_entity.*;
import FOMS.branch_manager.*;

/**
 * The AddStaff class is responsible for adding a staff member to the system.
 * It extends the ABaseAddStaff abstract class and implements the addSpecificRoleStaff method.
 * This class prompts the user to input staff details and adds them to the system.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class AddStaff extends ABaseAddStaff {
    /**
     * The default password used for new staff accounts.
     */
    private static final String DEFAULT_PASSWORD = "password";
    private BranchQuotaManager branchQuotaManager; 

    /**
     * Constructs a new AddStaff object.
     * @param scanner The Scanner object used for user input.
     * @param first A boolean indicating whether this is the first staff member being added.
     * @param branchQuotaManager The BranchQuotaManager to handle branch quotas.
     */
    public AddStaff(Scanner scanner, Boolean first, BranchQuotaManager branchQuotaManager) {
        super(scanner, first);
        this.branchQuotaManager = new BranchQuotaManager();  
    }

     /**
     * Constructs a new AddStaff object.
     * 
     * @param scanner The Scanner object used for user input.
     * @param first A boolean indicating whether this is the first staff member being added.
     */
    public AddStaff(Scanner scanner, Boolean first) {
        super(scanner, first);
        this.branchQuotaManager = new BranchQuotaManager();  

    }

    /**
     * Adds a staff member to the system.
     * @param filename The name of the file to write the updated credentials to.
     * @param credentials A map containing the current credentials in the system.
     */
    @Override
    public void addSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
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

        String salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(DEFAULT_PASSWORD, salt);

        UserCredentials newCredentials = new UserCredentials(name, "S", gender, age, branch, salt, hashedPassword, true);
        IStaffFilter filter = new BranchFilter(branch);
        List <UserCredentials> filteredList = filter.filter(credentialsList);
        int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);

        if (first == true && (totalStaff == 5 || totalStaff == 10)) {
            System.out.println("Does not meet quota ratio! Choose an action:");
            System.out.println("1. Add Manager");
            System.out.println("2. Remove Staff");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    AddManager addManager = new AddManager(new Scanner(System.in), false, branchQuotaManager);
                    addManager.addSpecificRoleStaff(filename, credentials);
                    credentials.put(loginID, newCredentials);
                    System.out.println("Staff added successfull");


                    break;
                case 2:
                    RemoveStaff removeStaff = new RemoveStaff(new Scanner(System.in), false);
                    removeStaff.removeRoleStaff(filename, credentials);
                    credentials.put(loginID, newCredentials);
                    System.out.println("Staff added successfull");


                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;  
                default:
                    System.out.println("Invalid choice. Please enter a valid number (1-3).");
                    break;
            }
        }

        else if (first == true && !branchQuotaManager.canAddStaff(branch, totalStaff)) {
            System.out.println("Cannot add staff. Branch quota exceeded.");
            return; 
        }

        else if (first == false || first == true){
            credentials.put(loginID, newCredentials);
            System.out.println("Staff added successfull");

        }


        writeToFile(filename, credentials);
    }
}
