package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import FOMS.FOMS_entity.*;

public class AddAdmin extends ABaseAddStaff {
    private static final String DEFAULT_PASSWORD = "password";

    public AddAdmin(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    @Override
    public void addSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials) {
        // Assuming the user provides all necessary details for adding an admin
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

        System.out.println("Enter branch (hit ENTER for none):");
        String branch = scanner.nextLine();
        branch = branch.isEmpty() ? "" : branch; // Treat empty input as null

        // Generate salt and hash password
        String salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(DEFAULT_PASSWORD, salt);

        // Creating and adding new admin credentials
        UserCredentials newCredentials = new UserCredentials(name, "A", gender, age, branch, salt, hashedPassword, true);
        credentials.put(loginID, newCredentials);

        // Optionally display staff or admin list for verification
        ViewFilteredStaffList.displayStaff(credentialsList);

        // Write updated credentials to the file
        writeToFile(filename, credentials);
    }
}
