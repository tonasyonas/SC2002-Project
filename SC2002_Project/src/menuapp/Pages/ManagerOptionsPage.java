package menuapp.Pages;

import FOMS.account_manager.*;
import FOMS.branch_manager.DisplayManagerBranchStaff;

import java.util.Map;
import java.util.Scanner;

import FOMS.FOMS_entity.BranchManager;
import FOMS.Pages.IPage;

public class ManagerOptionsPage implements IPage {

    public ManagerOptionsPage() {
    }

    public void addMenuItem() {
        // Logic to add a new menu item
        // Implement test cases to validate this functionality
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
    //
    }
    
    @Override
    public void startPage() {
        display();
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Edit Menu Item");
            System.out.println("3. Remove Menu Item");
            System.out.println("4. Manage Availability");
            System.out.println("5. Manage Variations");
            System.out.println("6. Display Staff List");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addMenuItem();
                    break;
                case "2":
                    editMenuItem();
                    break;
                case "3":
                    removeMenuItem();
                    break;
                case "4":
                    manageAvailability();
                    break;
                case "5":
                    manageVariations();
                    break;
                case "6":
                    displayStaffList();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!choice.equals("0"));
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
