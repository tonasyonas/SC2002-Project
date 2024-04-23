package FOMS.Pages;

import java.util.Scanner;

import menuapp.Pages.CustOrderPage;
import menuapp.Pages.StaffLoginPage;

public class StartUpPage implements IPage {
    private Scanner scanner;

    public StartUpPage() {
        this.scanner = new Scanner(System.in); // Initialize it once here
    }

    @Override
    public void startPage() {
        display();
        String userType = getInput();
        switch (userType) {
            case "customer":
                CustOrderPage custOrderPage = new CustOrderPage();
                custOrderPage.startPage();
                break;
            case "staff":
                StaffLoginPage staffLoginPage = new StaffLoginPage();
                staffLoginPage.startPage();
                break;
            default:
                System.out.println("Invalid input. Please enter 'customer' or 'staff'.");
                startPage(); // Retry if input is invalid
                break;
        }
    }

    @Override
    public void display() {
        System.out.println("Are you a customer or a staff member?");
        System.out.print("Enter 'customer' or 'staff': ");
    }

    @Override
    public String getInput() {
        String userType = scanner.nextLine().trim().toLowerCase();
        return userType;
    }

}
