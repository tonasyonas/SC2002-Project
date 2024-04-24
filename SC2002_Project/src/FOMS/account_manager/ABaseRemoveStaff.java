package FOMS.account_manager;

import FOMS.FOMS_entity.Staff;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Abstract base class for removing staff members.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 * 
 */
public abstract class ABaseRemoveStaff {
    protected Scanner scanner;
    protected Boolean first;

    /**
     * Constructs an ABaseRemoveStaff object.
     *
     * @param scanner The Scanner object to read input from the console.
     * @param first   Indicates whether it's the first staff member being removed.
     */
    public ABaseRemoveStaff(Scanner scanner, Boolean first) {
        this.scanner = scanner;
        this.first = first;
    }

    /**
     * Abstract method to be implemented by subclasses for removing specific staff roles.
     *
     * @param filename    The name of the file to write the updated staff details to.
     * @param credentials A map containing login IDs and corresponding UserCredentials objects.
     */
    protected abstract void removeSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials);

    /**
     * Writes the updated staff details to a file after removal.
     *
     * @param filename    The name of the file to write the updated staff details to.
     * @param credentials A map containing login IDs and corresponding UserCredentials objects.
     */
    protected void writeToFile(String filename, Map<String, UserCredentials> credentials) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, UserCredentials> entry : credentials.entrySet()) {
                UserCredentials userCredentials = entry.getValue();
                String line = userCredentials.getName() + ";" +
                        entry.getKey() + ";" + 
                        userCredentials.getRole() + ";" +
                        (userCredentials.getGender() == Staff.Gender.F ? "F" : "M") + ";" +
                        userCredentials.getAge() + ";" +
                        userCredentials.getBranch() + ";" +
                        userCredentials.getSalt() + ";" +
                        userCredentials.getHashedPassword() + ";" +
                        userCredentials.getNeedsPasswordReset();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Staff list updated in the file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
