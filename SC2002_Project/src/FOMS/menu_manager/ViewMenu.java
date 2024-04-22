package FOMS.menu_manager;

import java.util.Map;
// import java.util.Map.Entry;


public class ViewMenu {
    private MenuDisplay menuDisplay;
    private BranchSelector branchSelector;
    private MenuOrganizer menuOrganizer;

    public ViewMenu(MenuDisplay menuDisplay, BranchSelector branchSelector, MenuOrganizer menuOrganizer) {
        this.menuDisplay = menuDisplay;
        this.branchSelector = branchSelector;
        this.menuOrganizer = menuOrganizer;
    }

    public void displayMenuForBranch() {
        String branch = branchSelector.selectBranch();
        Map<String, MenuItem> menu = menuOrganizer.getMenuForBranch(branch);
        if (menu != null) {
            menuDisplay.displayMenu(branch, menu);
        } else {
            System.out.println("No menu available for this branch.");
        }
    }
}
