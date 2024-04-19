package FOMS.Pages;

import java.util.Scanner;
// import FOMS.account_manager.*;

public class StartUpPage implements Page {

    @Override
    public void display() {
        System.out.println("Welcome to the restaurant");
        System.out.println("Are you a customer or a staff member?");
        System.out.print("Enter 'customer' or 'staff': ");
    }

    @Override
    public void startPage() {
        this.display();
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine().trim().toLowerCase();
        if (userType.equals("customer")) {
            // Allow customers to proceed to the ordering system without logging in
            // Proceed to the CustOrderPage
        } else if (userType.equals("staff")) {
            // Proceed to StaffLoginPage
        } else {
            startPage();
        }
        scanner.close();
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine().trim().toLowerCase();
        return userType;
    }
}
