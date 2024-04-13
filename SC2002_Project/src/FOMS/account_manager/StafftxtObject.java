import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StafftxtObject {

    public static void main(String[] args) throws FileNotFoundException{
        String filename = "/Users/donovango/Documents/GitHub/SC2002-Project/SC2002_Project/src/FOMS/account_manager/staff_list.txt"; // Name of your text file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String staffName= parts[0];
                    String loginID = parts[1];
                    Staff.Role role = Staff.Role.valueOf(String.valueOf(parts[2].charAt(0)));
                    Staff.Gender gender= Staff.Gender.valueOf(String.valueOf(parts[3].charAt(0)));
                    //int age = Integer.parseInt(parts[4]);
                    String branchName = parts[4];

                    // Create a Staff object
                    Staff staff = new Staff(staffName, gender, 1, branchName);

                    // Now you can use the 'staff' object as needed
                    // For example, you could add it to a list or perform other operations
                    staff.print();
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

}
