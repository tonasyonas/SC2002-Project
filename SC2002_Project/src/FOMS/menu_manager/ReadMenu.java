package FOMS.menu_manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * The ReadMenu class provides methods to read menu items from a file and construct a map of menu items.
 * It reads menu items from a specified file, parses the lines, and creates MenuItem objects accordingly.
 * Menu items are stored in a map using a composite key of item name and branch.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ReadMenu {

    /**
     * Reads menu items from the specified file and constructs a map of menu items.
     * 
     * @param filename The path to the file containing the menu items.
     * @return A map containing the menu items, with composite keys of item name and branch.
     */
    public static Map<String, MenuItem> readMenuItems(String filename) {
        Map<String, MenuItem> menu = new HashMap<>();
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();  // Trim the line to remove any leading or trailing whitespace
                if (line.isEmpty()) {  // Skip empty lines
                    continue;
                }
    
                String[] parts = line.split(";");
                if (parts.length >=5 ) {
                    try {
                        String itemName = parts[0].trim();
                        double itemPrice = Double.parseDouble(parts[1].trim());
                        String itemBranch = parts[2].trim();
                        String itemType = parts[3].trim().toLowerCase();
                        boolean available = Boolean.parseBoolean(parts[4].trim());
                        String description = (parts.length > 5) ? parts[5].trim() : "No description";
                        MenuItem item = null;
                        switch (itemType) {
                            case "burger":
                                item = new Burger(itemName, itemPrice, itemBranch, available, description);
                                break;
                            case "drink":
                                item = new Drink(itemName, itemPrice, itemBranch, available, description);
                                break;
                            case "set meal":
                                item = new SetMeal(itemName, itemPrice, itemBranch, available, description);
                                break;
                            case "side":
                                item = new Side(itemName, itemPrice, itemBranch, available, description);
                                break;
                            default:
                                System.err.println("Unknown item type '" + itemType + "'");
                                continue;
                        }
    
                        if (item != null) {
                            menu.put(itemName + "#" + itemBranch, item); // Composite key of name and branch
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing item price: " + e.getMessage());
                    }
                } else {
                    System.err.println("Incorrectly formatted line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    
        return menu;
        
    }    
}
