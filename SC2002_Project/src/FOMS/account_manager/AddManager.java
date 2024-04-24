package FOMS.account_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import FOMS.FOMS_entity.*;
public class AddManager extends ABaseAddStaff {
    private static final String DEFAULT_PASSWORD = "password";

    public AddManager(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    @Override
    protected void addSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
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

        if (first == true && (totalStaff == 5 || totalStaff == 10) ){
            //remove manager or add staff
            System.out.println("Does not meet qutoa ratio! Remove a manager");
            Scanner RemoveStaffScanner = new Scanner(System.in);
            RemoveManager removeManager = new RemoveManager(RemoveStaffScanner,false);
            removeManager.EditStaffList(filename, credentials);
        }

        // Write updated credentials to the file
        writeToFile(filename, credentials);
    }
}
