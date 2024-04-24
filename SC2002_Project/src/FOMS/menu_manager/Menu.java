package FOMS.menu_manager;

import java.util.ArrayList;
/**
 * Represents a menu containing menu items.
 * Provides methods to manage menu items.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class Menu {
    private ArrayList<MenuItem> menuItems;

    /**
     * Constructs a new Menu object with an empty list of menu items.
     */
    public Menu() {
        menuItems = new ArrayList<>();
    }

    /**
     * Adds a MenuItem to the menu.
     * 
     * @param item The MenuItem to add to the menu.
     */
    public void addItem(MenuItem item) {
        this.menuItems.add(item);
    }
}
