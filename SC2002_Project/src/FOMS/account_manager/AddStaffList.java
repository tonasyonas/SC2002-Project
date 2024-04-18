package FOMS.account_manager;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;

public class AddStaffList extends AStaffListEditor {
    private Scanner scanner;
    private static final String DEFAULT_PASSWORD = "password";

    public AddStaffList(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
        // Get input for new staff member
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

        // Add new credentials to the map
        credentials.put(loginID, newCredentials);
        writeToFile(filename, credentials);
    }
}