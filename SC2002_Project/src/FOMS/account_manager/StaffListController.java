package FOMS.account_manager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import FOMS.FOMS_entity.*;


public class StaffListController {
    public void displayStaffListController (){
        Scanner scanner = new Scanner(System.in);
        String filename = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
        Map<String, UserCredentials> credentials = ReadStaffList.getStaffCredentials(filename);

        System.out.println("Choose operation:");
        System.out.println("1. Add staff");
        System.out.println("2. Remove staff");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Adding Staff: Please give details");
                Scanner addStaffScanner = new Scanner(System.in);
                AddStaffList addStaffList = new AddStaffList(addStaffScanner);
                addStaffList.EditStaffList(filename, credentials);
                break;
            case 2:
                // Remove staff
                System.out.println("Removing Staff: Please give details");
                Scanner RemoveStaffScanner = new Scanner(System.in);;
                RemoveStaffList removeStaffList = new RemoveStaffList(RemoveStaffScanner);
                removeStaffList.EditStaffList(filename, credentials);
                break;
            default:
                System.out.println("Invalid choice");
        }

        // Close scanner
        scanner.close();
    }
}
