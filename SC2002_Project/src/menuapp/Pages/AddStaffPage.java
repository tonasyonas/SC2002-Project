package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import FOMS.account_manager.*;


public class AddStaffPage {
    private Scanner scanner;
    private Map<String, UserCredentials> credentialsMap;
    private String filename;

    public AddStaffPage(Scanner scanner, Map<String, UserCredentials> credentialsMap, String filename) {
        this.scanner = scanner;
        this.credentialsMap = credentialsMap;
        this.filename = filename;
    }

    public void display() {
        System.out.println("Adding Staff: Please give details");
        AddStaff addStaff = new AddStaff(scanner, true);
        addStaff.addSpecificRoleStaff(filename, credentialsMap);
    }
}
