package FOMS.branch_manager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadBranchList {
    public static String[] getBranchIDs(String filename) {
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
        
        // Extract branch IDs and store them in a String array
        String[] branchIDs = new String[branchList.size()];
        for (int i = 0; i < branchList.size(); i++) {
            branchIDs[i] = branchList.get(i).getBranchID();
        }
        
        return branchIDs;
    }
}
