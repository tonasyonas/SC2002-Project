import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readReadStaffList {

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
                    char roleChar = parts[2].charAt(0); // Assuming parts[2] is a string containing a single character
                    Staff.Role role;

                    // Convert the character to the corresponding Role enum value
                    switch (roleChar) {
                        case 'A':
                            role = Staff.Role.A;
                            break;
                        case 'S':
                            role = Staff.Role.S;
                            break;
                        case 'M':
                            role = Staff.Role.M;
                            break;
                        default:
                            // Handle the case where the character doesn't represent any Role
                            // For example, you could throw an IllegalArgumentException
                            throw new IllegalArgumentException("Invalid role character: " + roleChar);
}
                    Staff.Gender gender= Staff.Gender.valueOf(String.valueOf(parts[3].charAt(0)));
                    //int age = Integer.parseInt(parts[4]);
                    String branchName = parts[4];
                    Staff staff = null;
        
                    if (role == Staff.Role.S){
                        staff = new Staff(staffName, gender, 1, branchName);
                    } 

                    else if (role == Staff.Role.M){
                        staff = new BranchManager(staffName, gender, 1, branchName);
                    } 

                    else if (role == Staff.Role.A){
                        staff = new Admin(staffName, gender, 1, branchName);
                    } 

                    // Now you can use the 'staff' object as needed
                    // For example, you could add it to a list or perform other operations
                if (staff != null) staff.print();
                else System.err.println("Failed to create staff object.");
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
