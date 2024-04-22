package FOMS.menu_manager;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
// import java.util.Map.Entry;

import FOMS.branch_manager.ReadBranchList;

public class ViewMenu {
    private Map<String, MenuItem> menuMap;
    private Map<String, Map<String, MenuItem>> branchMenus;
    private static final String BRANCH_FILE = "SC2002_Project/src/FOMS/branch_manager/branch_list.txt";
    private String[] branches = ReadBranchList.getBranchIDs(BRANCH_FILE); // The available branches.

    // Constructor that accepts a Map of menu items.
    public ViewMenu(Map<String, MenuItem> menuMap) {
        this.menuMap = menuMap;
        this.branchMenus = new HashMap<>();
        organizeByBranch();
    }

    private void organizeByBranch() {
        for (MenuItem item : menuMap.values()) {
            String branch = item.getBranch();
            branchMenus.computeIfAbsent(branch, k -> new HashMap<>()).put(item.getItem(), item);
        }
    }

    // Method to display all menu items.
    public void displayMenuForBranch(Scanner sc) {
        String branch = selectBranch(sc);
        Map<String, MenuItem> branchMenu = branchMenus.get(branch);
        if (branchMenu == null || branchMenu.isEmpty()) {
            System.out.println("The menu is currently empty for branch " + branch + ".");
        } else {
            System.out.println("Menu Items for branch " + branch + ":");
            int i = 1;
            for (MenuItem item : branchMenu.values()) {
                System.out.printf("%d. Name: %-20s Price: $%.2f\n", i++, item.getItem(), item.getCost());
            }
        }
    }

    private String selectBranch(Scanner sc) {
        System.out.println("Please select your branch:");
        for (int i = 0; i < branches.length; i++) {
            System.out.println((i + 1) + ". " + branches[i]);
        }
        int branchIndex = sc.nextInt() - 1;
        sc.nextLine(); // Consume newline
        if (branchIndex >= 0 && branchIndex < branches.length) {
            return branches[branchIndex];
        } else {
            System.out.println("Invalid branch selection. Please try again.");
            return selectBranch(sc); // Retry if invalid
        }
    }

}

