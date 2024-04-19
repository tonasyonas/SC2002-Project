package FOMS.account_manager;

import java.util.Map;
import java.util.Scanner;
//import FOMS.FOMS_entity.*;
//import java.util.List;
// import java.util.ArrayList;

public class AdminInterface {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Choose an option:");
            System.out.println("1. View Full Staff List");
            System.out.println("2. View Filtered Staff List");
            System.out.println("3. Edit Staff List");
            System.out.print("Enter your choice (1/2/3): ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Full Staff List:");
                    ViewStaffList viewStaffList = new ViewStaffList();
                    viewStaffList.displayAllStaff();
                    break;
                case 2:
                    System.out.println("Filtered Staff List:");
                    Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
                    ViewFilteredStaffList viewer = new ViewFilteredStaffList(credentialsMap);
                    viewer.filterStaff(sc);
                    break;
                
                case 3:
                    StaffListController displayController = new StaffListController();
                    displayController.displayStaffListController();
                    break;
                
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice > 0 && choice <= 3);
        
        sc.close();
    }
}