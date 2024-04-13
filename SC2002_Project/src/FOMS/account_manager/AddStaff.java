import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddStaff {
    public void addStaff(String staffName, String staffLoginID, String staffRole, String staffGender, int staffAge, String branchName) {
        String filePath = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
        
        try {
            // Open the file in append mode
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write staff details to the file separated by ';'
            bufferedWriter.write(staffName + ";" + staffLoginID + ";" + staffRole + ";" + staffGender + ";" + staffAge + ";" + branchName + "\n");

            // Close the writers
            bufferedWriter.close();
            fileWriter.close();
            
            System.out.println("Staff details successfully written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
