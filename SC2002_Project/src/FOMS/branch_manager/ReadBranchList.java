package FOMS.branch_manager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadBranchList {
    public static List<Branch> getBranchList(String filename) {
        List<Branch> branchList = new ArrayList<>(); // ArrayList to store branches
    
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
