package FOMS.branch_manager;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The AOpenCloseBranch class is an abstract class that provides common methods 
 * for reading and writing branch information to a text file.
 * It implements the IOpenCloseBranch interface.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public abstract class AOpenCloseBranch implements IOpenCloseBranch {

    /**
     * Reads branch information from a text file.
     * 
     * @param filename The name of the file to read from.
     * @return A list of strings representing branch information.
     */
    protected List<String> readBranchList(String filename) {
        List<String> branchList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                branchList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return branchList;
    }

    /**
     * Writes branch information to a text file.
     * 
     * @param filename The name of the file to write to.
     * @param branchList The list of strings representing branch information to write.
     */
    protected void writeToTextFile(String filename, List<String> branchList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String branchEntry : branchList) {
                writer.write(branchEntry + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
