package FOMS.menu_manager;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
// import java.util.Map.Entry;

/**
 * The ViewMenu class provides functionality to display menu items for a specific branch,
 * retrieve menu items by name or number, and display only available menu items for a branch.
 * It utilizes implementations of MenuDisplay, BranchSelector, and MenuOrganizer interfaces.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ViewMenu {
    private MenuDisplay menuDisplay;
    private IBranchSelector branchSelector;
    private MenuOrganizer menuOrganizer;

    /**
     * Constructs a ViewMenu object with the specified implementations of MenuDisplay, BranchSelector, and MenuOrganizer.
     * 
     * @param menuDisplay An implementation of MenuDisplay used to display menus.
     * @param branchSelector An implementation of BranchSelector used to select branches.
     * @param menuOrganizer An implementation of MenuOrganizer used to organize menus.
     */
    public ViewMenu(MenuDisplay menuDisplay, IBranchSelector branchSelector, MenuOrganizer menuOrganizer) {
        this.menuDisplay = menuDisplay;
        this.branchSelector = branchSelector;
        this.menuOrganizer = menuOrganizer;
    }

    /**
     * Displays the menu for the specified branch.
     * 
     * @param selectedBranch The branch for which the menu is to be displayed.
     */
    public void displayMenuForBranch(String selectedBranch) {
        Map<String, MenuItem> menu = menuOrganizer.getMenuForBranch(selectedBranch);
        if (menu != null) {
            menuDisplay.displayMenu(selectedBranch, menu);
        } else {
            System.out.println("No menu available for this branch.");
        }
    }

    /**
     * Retrieves a menu item by its name and branch.
     * 
     * @param item The name of the menu item.
     * @param branch The branch at which the menu item is available.
     * @return The MenuItem object if found, or null if not found.
     */
    public MenuItem getMenuItem(String item, String branch) {
        Map<String, MenuItem> menu = menuOrganizer.getMenuForBranch(branch);
        if (menu != null) {
            return menu.get(item);
        }
        return null;
    }

    /**
     * Retrieves a menu item by its position number and branch.
     * 
     * @param itemNumber The position number of the menu item.
     * @param branch The branch at which the menu item is available.
     * @return The MenuItem object if found, or null if not found.
     */
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
    
    /**
     * Displays the available menu items for the specified branch.
     * 
     * @param selectedBranch The branch for which available menu items are to be displayed.
     */
    public void displayAvailableMenuForBranch(String selectedBranch) {
        Map<String, MenuItem> fullMenu = menuOrganizer.getMenuForBranch(selectedBranch);
        if (fullMenu != null) {
            // Filter the menu to include only available items
            Map<String, MenuItem> availableMenu = fullMenu.entrySet().stream()
                .filter(entry -> entry.getValue().isAvailable())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            if (!availableMenu.isEmpty()) {
                menuDisplay.displayMenu(selectedBranch, availableMenu);
            } else {
                System.out.println("No available menu items for this branch.");
            }
        } else {
            System.out.println("No menu available for this branch.");
        }
    }
}
