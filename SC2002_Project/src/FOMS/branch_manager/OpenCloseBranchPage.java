package FOMS.branch_manager;

import java.util.Scanner;

/**
 * The OpenCloseBranchPage class provides functionality to open or close a branch.
 * It presents a menu to the user to choose between opening or closing a branch.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class OpenCloseBranchPage {

    /**
     * Displays a menu to the user for opening or closing a branch based on their choice.
     * 
     * @param scanner The scanner object to collect user input.
     */
    public static void openclosebranch(Scanner scanner) {
        System.out.println("Opening or closing Branch");
        System.out.println("Press 1 for Open Branch");
        System.out.println("Press 2 for Close Branch");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                OpenBranch.OpeningBranch(scanner);
                break;
            case 2:
                CloseBranch.ClosingBranch(scanner);
                break;
        }
    }
}
