package FOMS.menu_manager;

import java.util.Map;
// import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;


public class ViewMenu {
    private MenuDisplay menuDisplay;
    private BranchSelector branchSelector;
    private MenuOrganizer menuOrganizer;

    public ViewMenu(MenuDisplay menuDisplay, BranchSelector branchSelector, MenuOrganizer menuOrganizer) {
        this.menuDisplay = menuDisplay;
        this.branchSelector = branchSelector;
        this.menuOrganizer = menuOrganizer;
    }

    public void displayMenuForBranch(String selectedBranch) {
        Map<String, MenuItem> menu = menuOrganizer.getMenuForBranch(selectedBranch);
        if (menu != null) {
            menuDisplay.displayMenu(selectedBranch, menu);
        } else {
            System.out.println("No menu available for this branch.");
        }
    }

    public MenuItem getMenuItem(String item, String branch) {
        Map<String, MenuItem> menu = menuOrganizer.getMenuForBranch(branch);
        if (menu != null) {
            return menu.get(item);
        }
        return null;
    }

    public MenuItem getMenuItemByNumber(int itemNumber, String branch) {
        Map<String, MenuItem> menu = menuOrganizer.getMenuForBranch(branch);
        if (menu != null) {
            List<MenuItem> itemList = new ArrayList<>(menu.values());
            if (itemNumber >= 1 && itemNumber <= itemList.size()) {
                return itemList.get(itemNumber - 1);
            }
        }
        return null;
    }
    

}
