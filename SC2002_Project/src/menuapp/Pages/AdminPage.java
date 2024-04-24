package menuapp.Pages;

import java.util.Map;
import java.util.Scanner;
import FOMS.account_manager.*;
import FOMS.branch_manager.OpenCloseBranchPage;
import FOMS.order_manager.AddRemovePayment;
import FOMS.Pages.IPage;

public class AdminPage implements IPage {

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
            System.out.println("7. Open/Close Branch");
            System.out.println("8. Add/Remove Payment Method");
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
                    // Add staff
                    AddStaffPage addStaffPage = new AddStaffPage(sc, credentialsMap, filename);
                    addStaffPage.startPage();
                    break;
                case 4:
                    // Remove staff
                    RemoveStaffPage removeStaffPage = new RemoveStaffPage(sc, credentialsMap, filename);
                    removeStaffPage.startPage();
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
                case 7:
                    // New case for opening or closing branches
                    OpenCloseBranchPage.openclosebranch(sc);
                    break;
                case 8:
                    // For adding/removing payment methods
                    AddRemovePayment.addRemovePayment(sc);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);

    }

    @Override
    public void display() {
        System.out.println("Welcome, Admin!");
    }

    @Override
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        String userType = sc.nextLine().trim().toLowerCase();
        return userType;
    }

}