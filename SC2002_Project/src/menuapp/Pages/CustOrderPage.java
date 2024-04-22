package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import FOMS.Pages.IPage;
import FOMS.menu_manager.*;

public class CustOrderPage implements IPage{

    @Override
    public void startPage() {
        display();
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Choose an option:");
            System.out.println("0. Exit");
            System.out.println("1. Browse Menu");
            System.out.println("2. Make Payment");
            System.out.println("3. Track Order Status");
            System.out.print("Enter your choice (1/2/3): ");
            // Check if the input is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1/2/3).");
                sc.next(); // Consume invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    String filename = "SC2002_Project\\src\\FOMS\\menu_manager\\menu_list.txt";
                    Map<String, MenuItem> menuMap = ReadMenu.readMenuItems(filename);
                    if (menuMap != null) {
                        ViewMenu viewMenu = new ViewMenu(menuMap);
                        viewMenu.displayMenuForBranch(sc); 
                    } else {
                        System.out.println("Error: Unable to read menu items from file.");
                    }
                    break;
                case 2:
                // payment handling logic
                    break;
                case 3:
                // order status tracking logic
                    break;
                case 0: 
                    System.out.println("Exiting the ordering system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 0);
        sc.close();
    }

    @Override
    public void display() {
        System.out.println("Welcome to the ordering system!");
    }

}
