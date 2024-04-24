package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

public class AddStaffPage implements IPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    public AddStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    @Override
    public void startPage() {
        display();
        addStaffBasedOnChoice();
        goToOptionsPage();
    }

    @Override
    public void display() {
        System.out.println("Add Staff:");
        System.out.println("1 - Add Manager");
        System.out.println("2 - Add Staff");
        System.out.println("3 - Go Back to Options");
    }

    public void addStaffBasedOnChoice() {
        String choice = getInput();
        switch (choice) {
            case "1":
                addManager();
                break;
            case "2":
                addRegularStaff();
                break;
            case "3":
                System.out.println("Returning to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                addStaffBasedOnChoice();
        }
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    private void addManager() {
        AddManager addManager = new AddManager(scanner, true);
        addManager.addSpecificRoleStaff(filename, credentialsMap);
    }

    private void addRegularStaff() {
        AddStaff addStaff = new AddStaff(scanner, true);
        addStaff.addSpecificRoleStaff(filename, credentialsMap);
    }

    private void goToOptionsPage() {
        IPage optionsPage = new AdminPage();
        optionsPage.startPage();
    }
}
