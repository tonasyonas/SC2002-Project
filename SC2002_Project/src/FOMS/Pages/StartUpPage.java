package FOMS.Pages;

import java.util.Scanner;

public class StartUpPage implements Page {

    @Override
    public void startPage() {
        this.display();
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.nextLine().trim().toLowerCase();
        if (userType.equals("customer")) {
            //go to customer page
            System.out.println("Yay cuistomer");
        } else if (userType.equals("staff")) {
            //go to login page
            System.out.println("Yay staff");
        }
        else {
            startPage();
        }
    }
    
    @Override
    public void display() {
        System.out.println("Welcome to restaurant");
        System.out.println("Are you a customer or a staff");
    }

    @Override
    public void getInput() {

    }
}
