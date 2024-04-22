package FOMS.branch_manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AOpenCloseBranch implements IOpenCloseBranch {
    protected void writeToTextFile(String filename, String branchID, String branchName, int quota) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("\n" + branchID + ";" + branchName + ";" + quota);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
}
