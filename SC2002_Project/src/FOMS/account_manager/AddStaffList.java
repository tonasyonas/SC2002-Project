package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import FOMS.FOMS_entity.*;

/**
 * The AddStaffList class is responsible for adding a new staff member to the system and editing the staff list.
 * It extends the AStaffListEditor abstract class and implements the EditStaffList method.
 * This class prompts the user to input details of the new staff member and adds them to the system.
 */
public class AddStaffList extends AStaffListEditor {
    /**
     * The Scanner object used for user input.
     */
    private Scanner scanner;
    
    /**
     * The default password used for new staff accounts.
     */
    private static final String DEFAULT_PASSWORD = "password";
    
    /**
     * A boolean indicating whether this is the first staff member being added.
     */
    Boolean first = true;

    /**
     * Constructs a new AddStaffList object.
     * @param scanner The Scanner object used for user input.
     * @param first A boolean indicating whether this is the first staff member being added.
     */
    public AddStaffList(Scanner scanner, Boolean first) {
        this.scanner = scanner;
        this.first = first;
    }

    /**
     * Edits the staff list by adding a new staff member to the system.
     * @param filename The name of the file to write the updated credentials to.
     * @param credentials A map containing the current credentials in the system.
     */
    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
       
        List<UserCredentials> credentialsList = new ArrayList<>(credentials.values());
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter login ID:");
        String loginID = scanner.nextLine();

        System.out.println("Enter role:");
        String role = scanner.nextLine();

        System.out.println("Enter gender (M/F):");
        String genderStr = scanner.nextLine();
        Staff.Gender gender = genderStr.equalsIgnoreCase("F") ? Staff.Gender.F : Staff.Gender.M;

        System.out.println("Enter age:");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter branch:");
        String branch = scanner.nextLine();

        String salt = PasswordUtils.getSalt();

        String hashedPassword = PasswordUtils.hashPassword(DEFAULT_PASSWORD, salt);

        UserCredentials newCredentials = new UserCredentials(name, role, gender, age, branch, salt, hashedPassword, true);
        credentials.put(loginID, newCredentials);
        IStaffFilter filter = new BranchFilter(branch);
        List <UserCredentials> filteredList = filter.filter(credentialsList);
        int totalStaff = ViewFilteredStaffList.displayStaff(filteredList);

        if (role.equals("S") && (first == true)){
            if (totalStaff == 5 || totalStaff == 10){
                System.out.println("Does not meet qutoa ratio! Add a manager");
                Scanner addStaffScanner1 = new Scanner(System.in);
                AddStaffList addStaffList = new AddStaffList(addStaffScanner1, false);
                addStaffList.EditStaffList(filename, credentials);
            }
        }

        else if (role.equals("M") && (first == true)){
            System.out.println("Does not meet qutoa ratio! Remove a manager");
            Scanner RemoveStaffScanner = new Scanner(System.in);
            RemoveStaffList removeStaffList = new RemoveStaffList(RemoveStaffScanner,false);
            removeStaffList.EditStaffList(filename, credentials);
        }

        
        writeToFile(filename, credentials);
       

    }
}
