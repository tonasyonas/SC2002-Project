package FOMS.menu_manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ReadMenu {

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
                if (parts.length == 4) {
                    try {
                        String itemName = parts[0].trim();
                        double itemPrice = Double.parseDouble(parts[1].trim());
                        String itemBranch = parts[2].trim(); // Currently not used, but could be in future enhancements
                        String itemType = parts[3].trim().toLowerCase();

                        MenuItem item = null;
                        switch (itemType) {
                            case "burger":
                                item = new Burger(itemName, itemPrice);
                                break;
                            case "drink":
                                item = new Drink(itemName, itemPrice);
                                break;
                            case "set meal":
                                item = new SetMeal(itemName, itemPrice);
                                break;
                            case "side":
                                item = new Side(itemName, itemPrice);
                                break;
                            default:
                                System.err.println("Unknown item type '" + itemType + "'");
                                continue;
                        }

                        if (item != null) {
                            menu.put(itemName + "#" + itemBranch, item); // Using a composite key of name and branch
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
