package menuapp.Pages;

import FOMS.account_manager.*;
import FOMS.branch_manager.DisplayManagerBranchStaff;
import FOMS.FOMS_entity.BranchManager;
import FOMS.Pages.IPage;
import java.util.Map;
import java.util.Scanner;

public class ManagerOptionsPage implements IPage {

    private BranchManager branchmanager;
    private Map<String, UserCredentials> staffCredentials;

    public ManagerOptionsPage(BranchManager branchmanager) {
        this.branchmanager = branchmanager;
        this.staffCredentials = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
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
        System.out.println("4. Display Staff List");
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
                    EditMenuItemPage editMenuItemPage = new EditMenuItemPage(branchmanager);
                    editMenuItemPage.startPage();
                    break;
                case 3:
                    RemoveMenuItemPage removeMenuItemPage = new RemoveMenuItemPage(branchmanager);
                    removeMenuItemPage.startPage();
                    break;
                case 4:
                    displayStaffList();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number (0-6).");
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
