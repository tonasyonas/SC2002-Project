package FOMS.menu_manager;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
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
