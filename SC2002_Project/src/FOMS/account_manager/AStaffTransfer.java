package FOMS.account_manager;
import FOMS.FOMS_entity.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

    /**
     * Abstract base class for transferring staff members.
     * 
     * This abstract class provides a base implementation for transferring staff members and defines a method for
     * writing staff details to a file. Subclasses can extend this class and provide custom implementations for
     * transferring staff members.
     * 
     * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
     * @version 1.0
     * @since 2024-04-24
     */
public abstract class AStaffTransfer {
    protected Scanner scanner;

    /**
     * Constructs an AStaffTransfer object with the specified Scanner.
     * 
     * @param scanner The Scanner object used for user input.
     */

    public AStaffTransfer(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Abstract method to be implemented by subclasses for transferring staff members.
     * 
     * @param filename The name of the file to write the updated credentials to.
     * @param credentials A map containing the current credentials in the system.
     */
    public abstract void transfer(String filename, Map<String, UserCredentials> credentials);

    /**
     * Writes staff details to a file.
     * 
     * @param filename The name of the file to write to.
     * @param credentials A map containing the login IDs as keys and the corresponding UserCredentials objects as values.
     */
    public void writeToFile(String filename, Map<String, UserCredentials> credentials) {
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
