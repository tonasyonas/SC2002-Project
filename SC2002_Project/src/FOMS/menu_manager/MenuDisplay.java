package FOMS.menu_manager;
import java.util.Map;

/**
 * Defines an interface for displaying a menu.
 * Implementing classes should provide functionality to display a menu for a specific branch.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface MenuDisplay {
    /**
     * Displays the menu items for a specific branch.
     * 
     * @param branchName The name of the branch for which the menu is being displayed.
     * @param menuItems A map containing the menu items, where the key is the item name and the value is the MenuItem object.
     */
    void displayMenu(String branchName, Map<String, MenuItem> menuItems);
}
