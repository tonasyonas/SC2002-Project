package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import FOMS.FOMS_entity.*;

/**
 * Class for adding administrative staff members.
 * 
 * This class extends ABaseAddStaff and provides functionality to add administrative staff members
 * to the system. It prompts the user for necessary details such as name, login ID, gender, age, and branch.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class AddAdmin extends ABaseAddStaff {
    private static final String DEFAULT_PASSWORD = "password";

    /**
     * Constructs an AddAdmin object.
     *
     * @param scanner The Scanner object to read input from the console.
     * @param first   Indicates whether it's the first staff member being added.
     */
    public AddAdmin(Scanner scanner, Boolean first) {
        super(scanner, first);
    }

    /**
     * Adds administrative staff members to the system.
     * 
     * This method prompts the user for the name, login ID, gender, age, and branch of the administrative staff member.
     * It then generates a default password, creates UserCredentials object, and adds it to the provided credentials map.
     * Finally, it displays the updated list of staff members and writes the changes to the specified file.
     *
     * @param filename    The name of the file to write the staff details to.
     * @param credentials A map containing login IDs and corresponding UserCredentials objects.
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

        System.out.println("Enter branch (hit ENTER for none):");
        String branch = scanner.nextLine();
        branch = branch.isEmpty() ? "" : branch; 

        String salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(DEFAULT_PASSWORD, salt);

        UserCredentials newCredentials = new UserCredentials(name, "A", gender, age, branch, salt, hashedPassword, true);
        credentials.put(loginID, newCredentials);

        ViewFilteredStaffList.displayStaff(credentialsList);

        writeToFile(filename, credentials);
    }
}
