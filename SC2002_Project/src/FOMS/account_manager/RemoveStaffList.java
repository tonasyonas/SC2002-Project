import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;

public class RemoveStaffList implements IStaffListEditor {
    private Scanner scanner;

    @Override
    public void EditStaffList(String filename, Map<String, UserCredentials> credentials) {
        // Assuming the user provides the login ID of the staff member to remove
        System.out.println("Enter login ID of the staff member to remove:");
        String loginIDToRemove = scanner.nextLine();

        // Check if the login ID exists in the credentials map
        if (credentials.containsKey(loginIDToRemove)) {
            // Remove the staff member with the specified login ID
            credentials.remove(loginIDToRemove);
            System.out.println("Staff member removed successfully.");
        } else {
            System.out.println("Staff member with the provided login ID does not exist.");
        }

        // Write the updated credentials back to the file
        writeToFile(filename, credentials);
    }
}