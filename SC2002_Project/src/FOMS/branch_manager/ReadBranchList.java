package FOMS.branch_manager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReadBranchList class provides functionality to read a list of branches from a file.
 * It reads branch information from a text file and constructs Branch objects.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ReadBranchList {
    /**
     * Reads branch information from the specified file and constructs a list of Branch objects.
     * 
     * @param filename The name of the file containing branch information.
     * @return A list of Branch objects.
     */
    public static List<Branch> getBranchList(String filename) {
        List<Branch> branchList = new ArrayList<>(); 
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String branchID = parts[0];
                    String branchName = parts[1];
                    int quota = Integer.parseInt(parts[2]);
                    
                    branchList.add(new Branch(branchID, branchName, quota));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return branchList;
    }
}
