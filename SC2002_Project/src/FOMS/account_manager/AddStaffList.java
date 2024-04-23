package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import FOMS.FOMS_entity.*;

public class AddStaffList extends AStaffListEditor {
    private Scanner scanner;
    private static final String DEFAULT_PASSWORD = "password";
    Boolean first = true;

    public AddStaffList(Scanner scanner, Boolean first) {
        this.scanner = scanner;
        this.first = first;
    }

    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
        // Get input for new staff member
       
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

        // Generate salt
        String salt = PasswordUtils.getSalt();

        // Hash default password with generated salt
        String hashedPassword = PasswordUtils.hashPassword(DEFAULT_PASSWORD, salt);

        // Create new UserCredentials object
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

        // Add new credentials to the map
        
        writeToFile(filename, credentials);
       

    }
}
