package FOMS.branch_manager;


import java.util.Scanner;



public class OpenCloseBranchPage {

    public static void openclosebranch() {
        Scanner scanner = new Scanner(System.in);
      

        System.out.println("Opening or closing Branch");
        System.out.println("Press 1 for Open Branch");
        System.out.println("Press 2 for Close Branch");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                OpenBranch.OpeningBranch();
                break;
            case 2:
                CloseBranch.ClosingBranch();
                break;

        }
       
        scanner.close();
    }
}
