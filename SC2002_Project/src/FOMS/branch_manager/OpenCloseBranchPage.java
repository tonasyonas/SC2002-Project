package FOMS.branch_manager;

import java.util.Scanner;

public class OpenCloseBranchPage {

    public static void openclosebranch(Scanner scanner) {
        // Do not create a new Scanner here; use the one passed as an argument
        System.out.println("Opening or closing Branch");
        System.out.println("Press 1 for Open Branch");
        System.out.println("Press 2 for Close Branch");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

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
