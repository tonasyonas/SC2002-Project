package FOMS.account_manager;
import FOMS.FOMS_entity.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public abstract class ABaseAddStaff {
    protected Scanner scanner;
    protected Boolean first;

    public ABaseAddStaff(Scanner scanner, Boolean first) {
        this.scanner = scanner;
        this.first = first;
    }

    // Abstract method to be implemented by subclasses for specific staff role addition
    protected abstract void addSpecificRoleStaff(String filename, Map<String, UserCredentials> credentials);

    // General method to write any kind of staff details to file
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
