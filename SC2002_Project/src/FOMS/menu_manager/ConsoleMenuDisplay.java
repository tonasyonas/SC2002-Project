package FOMS.menu_manager;
import java.util.Map;

/**
 * A class for displaying the menu items in the console.
 * Implements the MenuDisplay interface.
 * It displays the menu items of a specific branch in a formatted manner in the console.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ConsoleMenuDisplay implements MenuDisplay {
    /**
     * Displays the menu items of a specific branch in the console.
     * If the menu is empty or null, a message indicating that the menu is empty is displayed.
     *
     * @param branchName The name of the branch for which the menu items are displayed.
     * @param menuItems  A map containing the menu items, where the key is the name of the item and the value is the MenuItem object.
     */
    @Override
    public void displayMenu(String branchName, Map<String, MenuItem> menuItems) {
        if (menuItems == null || menuItems.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }

        System.out.println("Menu Items for branch " + branchName + ": ");
        int i = 1;
        for (MenuItem item : menuItems.values()) {
            System.out.printf("%d. Name: %-20s Price: $%.2f %-20s Description: %s\n", i++, item.getItem(), item.getCost(), " ", item.getDescription());
        }
    }

}
