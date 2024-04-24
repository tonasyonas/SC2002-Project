package FOMS.account_manager;
import FOMS.FOMS_entity.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public abstract class AStaffTransfer {
    protected Scanner scanner;

    public AStaffTransfer(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void transfer(String filename, Map<String, UserCredentials> credentials);

    public void writeToFile(String filename, Map<String, UserCredentials> credentials) {
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
