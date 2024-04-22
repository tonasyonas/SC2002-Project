package FOMS.menu_manager;
import java.util.Map;

public class ConsoleMenuDisplay implements MenuDisplay {
    @Override
    public void displayMenu(String branchName, Map<String, MenuItem> menuItems) {
        if (menuItems == null || menuItems.isEmpty()) {
            System.out.println("The menu is currently empty.");
            return;
        }

        System.out.println("Menu Items for branch " + branchName + ": ");
        int i = 1;
        for (MenuItem item : menuItems.values()) {
            System.out.printf("%d. Name: %-20s Price: $%.2f\n", i++, item.getItem(), item.getCost());
        }
    }

}
