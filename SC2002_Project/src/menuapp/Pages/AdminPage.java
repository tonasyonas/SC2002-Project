package menuapp.Pages;

import java.util.Map;
import java.util.Scanner;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

public class AdminPage implements IPage{

    @Override
    public void startPage() {
        display();
        int choice;

        String filename = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials(filename);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Choose an option:");
            System.out.println("0. Exit");
            System.out.println("1. View Full Staff List");
            System.out.println("2. View Filtered Staff List");
            System.out.println("3. Add Staff");
            System.out.println("4. Remove Staff");
            System.out.println("5. Promote Staff");
            System.out.println("6. Transfer Staff");
            System.out.print("Enter your choice: ");
            // Check if the input is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1/2/3).");
                sc.next(); // Consume invalid input
            }
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
                   
                    ViewFilteredStaffList viewer = new ViewFilteredStaffList(credentialsMap);
                    viewer.filterStaff(sc);
                    break;
                case 3:
                    System.out.println("Adding Staff: Please give details");
                    Scanner addStaffScanner = new Scanner(System.in);
                    AddStaffList addStaffList = new AddStaffList(addStaffScanner);
                    addStaffList.EditStaffList(filename, credentialsMap);
                    break;
                case 4:
                    // Remove staff
                    System.out.println("Remove Staff");
                    Scanner RemoveStaffScanner = new Scanner(System.in);;
                    RemoveStaffList removeStaffList = new RemoveStaffList(RemoveStaffScanner);
                    removeStaffList.EditStaffList(filename, credentialsMap);
                    break;
                case 5:
                    PromoteStaff promoteStaff = new PromoteStaff(sc);
                    // Pass the filename and credentials map to EditStaffList method
                    promoteStaff.EditStaffList(filename, credentialsMap);
                    break;
                case 6:
                    TransferStaff transferStaff = new TransferStaff(sc);
                    // Pass the filename and credentials map to EditStaffList method
                    transferStaff.EditStaffList(filename, credentialsMap);
                    break;
                
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);
        
        sc.close();
    }
    
    @Override
    public void display() {
        System.out.println("Welcome, admin!");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine().trim().toLowerCase();
        return userType;
    }

}