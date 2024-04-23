package menuapp.Pages;

import java.util.Scanner;
import FOMS.Pages.IPage;

public class BranchManagerOptionsPage implements IPage {

    @Override
    public void startPage() {
        display();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, manager");
        String choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Manager Options");
            System.out.println("2. Staff Options");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Manager Options selected.");
                    ManagerOptionsPage managerOptionsPage = new ManagerOptionsPage();
                    managerOptionsPage.startPage();
                    break;
                case "2":
                    System.out.println("Staff Options selected.");
                    // Call the staff options method here
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
        System.out.println("Welcome to Manager Options!");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.trim().toLowerCase();
    }
}