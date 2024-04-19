package FOMS.account_manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, UserCredentials> credentials = new HashMap<>();
        String filename = "staff_credentials.txt";

        // Instantiate AddStaffList and RemoveStaffList
        AddStaffList addStaffList = new AddStaffList(scanner);
    

        // Simulate adding staff members
        System.out.println("Adding new staff members:");
        addStaffList.EditStaffList(filename, credentials);

        // Close scanner
        scanner.close();
    }
}
