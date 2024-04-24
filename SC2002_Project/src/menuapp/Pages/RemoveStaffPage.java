package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

public class RemoveStaffPage implements IPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    public RemoveStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    @Override
    public void startPage() {
        display();
        removeStaffBasedOnChoice();
        goToOptionsPage();
    }

    @Override
    public void display() {
        System.out.println("Add Staff:");
        System.out.println("1 - Remove Manager");
        System.out.println("2 - Remove Staff");
        System.out.println("3 - Go Back to Options");
    }

    public void removeStaffBasedOnChoice() {
        String choice = getInput();
        switch (choice) {
            case "1":
                removeManager();
                break;
            case "2":
                removeRegularStaff();
                break;
            case "3":
                System.out.println("Returning to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                removeStaffBasedOnChoice();
        }
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    private void removeManager() {
        RemoveManager removeManager = new RemoveManager(scanner, true);
        removeManager.removeSpecificRoleStaff(filename, credentialsMap);
    }

    private void removeRegularStaff() {
        RemoveStaff removeStaff = new RemoveStaff(scanner, true);
        removeStaff.removeSpecificRoleStaff(filename, credentialsMap);
    }

    private void goToOptionsPage() {
        IPage optionsPage = new AdminPage();
        optionsPage.startPage();
    }
}
