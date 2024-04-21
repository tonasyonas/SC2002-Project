package FOMS.Pages;

import java.util.Scanner;

// import FOMS.account_manager.*;

import menuapp.Pages.CustOrderPage;
import menuapp.Pages.StaffLoginPage;

public class StartUpPage implements IPage {

    @Override
    public void startPage() {
        this.display();
        String userType = getInput();
        if (userType.equals("customer")) {
            CustOrderPage custOrderPage = new CustOrderPage();
        } else if (userType.equals("staff")) {
            StaffLoginPage staffLoginPage = new StaffLoginPage(); 
            staffLoginPage.startPage();
        } else {
            startPage();
        }
    }

    @Override
    public void display() {
        System.out.println("Welcome to the restaurant");
        System.out.println("Are you a customer or a staff member?");
        System.out.print("Enter 'customer' or 'staff': ");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine().trim().toLowerCase();
        return userType;
    }

}
