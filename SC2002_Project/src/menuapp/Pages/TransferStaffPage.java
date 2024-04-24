package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;
import FOMS.Pages.IPage;

public class TransferStaffPage implements IPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    public TransferStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    @Override
    public void startPage() {
        display();
        transferStaffBasedOnChoice();
        goToOptionsPage();
    }

    @Override
    public void display() {
        System.out.println("Transfer Staff:");
        System.out.println("1 - Transfer Manager");
        System.out.println("2 - Transfer Staff");
        System.out.println("3 - Go Back to Options");
    }

    public void transferStaffBasedOnChoice() {
        String choice = getInput();
        switch (choice) {
            case "1":
                transferManager();
                break;
            case "2":
                transferRegularStaff();
                break;
            case "3":
                System.out.println("Returning to previous menu...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                transferStaffBasedOnChoice();
        }
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    private void transferManager() {
        ConcreteTransferManager transferM = new ConcreteTransferManager(scanner);
        transferM.transfer(filename, credentialsMap);
    }

    private void transferRegularStaff() {
        AddStaff addStaff = new AddStaff(scanner, true);
        addStaff.addSpecificRoleStaff(filename, credentialsMap);
    }

   

    private void goToOptionsPage() {
        IPage optionsPage = new AdminPage();
        optionsPage.startPage();
    }
}
