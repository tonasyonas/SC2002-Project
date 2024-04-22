package FOMS.menu_manager;

import java.util.Scanner;

public class ConsoleBranchSelector implements BranchSelector {
    private Scanner scanner;
    private String[] branches;

    public ConsoleBranchSelector(Scanner scanner, String[] branches) {
        this.scanner = scanner;
        this.branches = branches;
    }

    @Override
    public String selectBranch() {
        System.out.println("Please select your branch:");
        for (int i = 0; i < branches.length; i++) {
            System.out.println((i + 1) + ". " + branches[i]);
        }

        int branchIndex = -1;
        do {
            try {
                System.out.print("Enter your choice (1-" + branches.length + "): ");
                branchIndex = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            if (branchIndex < 0 || branchIndex >= branches.length) {
                System.out.println("Invalid branch selection. Please try again.");
            }
        } while (branchIndex < 0 || branchIndex >= branches.length);

        return branches[branchIndex];
    }
}
