package FOMS.menu_manager;

import java.util.ArrayList;
public class Menu {
    private ArrayList<MenuItem> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        this.menuItems.add(item);
    }
}
