package FOMS.account_manager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;

public class StaffListController{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
        Map<String, UserCredentials> credentials = ReadStaffList.getStaffCredentials(filename);
        // Instantiate AddStaffList
        AddStaffList addStaffList = new AddStaffList(scanner);

        // Simulate adding staff members
        System.out.println("Adding new staff members:");
        addStaffList.EditStaffList(filename, credentials);

        // Close scanner
        scanner.close();
    }
}
