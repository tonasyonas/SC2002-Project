package menuapp.Pages;

import FOMS.account_manager.*;
import FOMS.branch_manager.DisplayManagerBranchStaff;

import java.util.Map;
import java.util.Scanner;

import FOMS.FOMS_entity.BranchManager;
import FOMS.Pages.IPage;

public class ManagerOptionsPage implements IPage {

    private BranchManager branchmanager;
    private Map<String, UserCredentials> staffCredentials;

    public ManagerOptionsPage(BranchManager branchmanager) {
        this.branchmanager = branchmanager;
        this.staffCredentials = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
    }

    public void editMenuItem() {
        // Logic to edit an existing menu item
        // Implement test cases to validate this functionality
    }

    public void removeMenuItem() {
        // Logic to remove an existing menu item
        // Implement test cases to validate this functionality
    }

    public void manageAvailability() {
        // Logic to manage the availability of menu items
        // Implement test cases to validate this functionality
    }

    public void manageVariations() {
        // Logic to manage variations in menu items and prices among branches
        // Implement test cases to validate this functionality
    }

    public void displayStaffList() {
        DisplayManagerBranchStaff branchlist = new DisplayManagerBranchStaff(staffCredentials);
        branchlist.displaystaffDetails(branchmanager);
    }
    
    @Override
    public void startPage() {
        display();
        Scanner scanner = new Scanner(System.in);
        String choice;
        int choiceint;
        System.out.println("1. Add Menu Item");
        System.out.println("2. Edit Menu Item");
        System.out.println("3. Remove Menu Item");
        System.out.println("4. Manage Availability");
        System.out.println("5. Manage Variations");
        System.out.println("6. Display Staff List");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            try{
                choiceint = Integer.parseInt(choice);
            }
            catch(Exception e){
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            switch (choiceint) {
                case 1:
                    // Create an instance of AddMenuItemPage and start it
                    AddMenuItemPage addMenuItemPage = new AddMenuItemPage(branchmanager);
                    addMenuItemPage.startPage();
                    break;
                case 2:
                    editMenuItem();
                    break;
                case 3:
                    removeMenuItem();
                    break;
                case 4:
                    manageAvailability();
                    break;
                case 5:
                    manageVariations();
                    break;
                case 6:
                    displayStaffList();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 0-6.");
                    break;
            }
        } while (!choice.equals(0));
        scanner.close();
    }

    @Override
    public void display() {
        System.out.println("Manager Options:");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim().toLowerCase();
        // scanner.close(); // Do not close the scanner here to avoid closing System.in stream prematurely.
        return userInput;
    }

}
