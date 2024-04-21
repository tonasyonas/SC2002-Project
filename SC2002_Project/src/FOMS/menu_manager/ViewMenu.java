package FOMS.menu_manager;

import java.util.Map;
// import java.util.HashMap;
// import java.util.Map.Entry;

public class ViewMenu {
    private Map<String, MenuItem> menuMap;

    // Constructor that accepts a Map of menu items.
    public ViewMenu(Map<String, MenuItem> menuMap) {
        this.menuMap = menuMap;
    }

    // Method to display all menu items.
    public void displayMenu() {
        if (menuMap.isEmpty()) {
            System.out.println("The menu is currently empty.");
        } else {
            System.out.println("Menu Items:");
            for (Map.Entry<String, MenuItem> entry : menuMap.entrySet()) {
                String key = entry.getKey();
                MenuItem item = entry.getValue();
                System.out.printf("Name: %-20s Price: $%.2f\n", item.getItem(), item.getCost());
            }
        }
    }

    public static void main(String[] args) {
        String filename = "SC2002_Project\\src\\FOMS\\menu_manager\\menu_list.txt"; 
        Map<String, MenuItem> menuItems = ReadMenu.readMenuItems(filename);
        
        ViewMenu viewMenu = new ViewMenu(menuItems);
        viewMenu.displayMenu();
    }
}

