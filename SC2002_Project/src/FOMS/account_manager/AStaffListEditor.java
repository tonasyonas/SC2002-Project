/**
 * The AStaffListEditor class provides a base implementation for editing staff lists.
 * It implements the IStaffListEditor interface and defines a method for writing staff details to a file.
 */
package FOMS.account_manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import FOMS.FOMS_entity.*;

public abstract class AStaffListEditor implements IStaffListEditor {

    /**
     * Writes staff details to a file.
     * @param filename The name of the file to write to.
     * @param credentials A map containing the login IDs as keys and the corresponding UserCredentials objects as values.
     */
    protected void writeToFile(String filename, Map<String, UserCredentials> credentials) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, UserCredentials> entry : credentials.entrySet()) {
                UserCredentials userCredentials = entry.getValue();
                String line = userCredentials.getName() + ";" +
                        entry.getKey() + ";" + // Login ID
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
