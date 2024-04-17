package FOMS.account_manager;

import FOMS.FOMS_entity.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    do {
        System.out.println("Choose an option:");
        System.out.println("1. View full staff list");
        System.out.println("2. View filtered staff list");
        System.out.print("Enter your choice (1/2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Full Staff List:");
                ViewStaffList viewStaffList = new ViewStaffList();
                viewStaffList.displayAllStaff();
                break;
            case 2:
                Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
                ViewFilteredStaffList viewer = new ViewFilteredStaffList(credentialsMap);
                viewer.filterStaff();
            default:
                System.out.println("Invalid choice. Please choose again.");
                break;
        }
    } while (choice > 0 && choice <= 2);
}