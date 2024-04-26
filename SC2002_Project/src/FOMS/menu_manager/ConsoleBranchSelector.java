package FOMS.menu_manager;

import java.util.Scanner;

/**
 * A class for selecting branches from the console.
 * Implements the BranchSelector interface.
 * It allows users to select a branch from a list displayed in the console.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ConsoleBranchSelector implements IBranchSelector {
    private Scanner scanner;
    private String[] branches;

    /**
     * Constructs a ConsoleBranchSelector with the specified scanner and branches.
     *
     * @param scanner  The scanner object used for input.
     * @param branches An array containing the names of the branches.
     */
    public ConsoleBranchSelector(Scanner scanner, String[] branches) {
        this.scanner = scanner;
        this.branches = branches;
    }

    /**
     * Displays the list of branches and prompts the user to select one.
     *
     * @return The name of the selected branch.
     */
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
