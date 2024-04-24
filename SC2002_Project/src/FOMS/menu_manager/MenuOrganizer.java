package FOMS.menu_manager;
import java.util.Map;
import java.util.HashMap;

/**
 * The MenuOrganizer class organizes menu items by branch.
 * It provides methods to retrieve menu items for a specific branch.
 * 
 * Menu items are organized into a map structure, where each branch has its own map of menu items.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class MenuOrganizer {
    private Map<String, MenuItem> menuMap;
    private Map<String, Map<String, MenuItem>> branchMenus;

    /**
     * Constructs a MenuOrganizer with the specified menu map.
     * 
     * @param menuMap The map containing all menu items.
     */
    public MenuOrganizer(Map<String, MenuItem> menuMap) {
        this.menuMap = menuMap;
        this.branchMenus = new HashMap<>();
        organizeByBranch();
    }

    /**
     * Organizes menu items by branch.
     * This method populates the branchMenus map with menu items grouped by branch.
     */
    private void organizeByBranch() {
        for (MenuItem item : menuMap.values()) {
            String branch = item.getBranch();
            branchMenus.computeIfAbsent(branch, k -> new HashMap<>()).put(item.getItem(), item);
        }
    }

    /**
     * Retrieves the menu items for the specified branch.
     * 
     * @param branch The branch for which to retrieve menu items.
     * @return A map containing the menu items for the specified branch, or null if the branch does not exist.
     */
    public Map<String, MenuItem> getMenuForBranch(String branch) {
        return branchMenus.get(branch);
    }

}
