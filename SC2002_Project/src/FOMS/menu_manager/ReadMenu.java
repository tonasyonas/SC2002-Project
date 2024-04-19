package FOMS.menu_manager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import FOMS.FOMS_entity.*;

public class ReadMenu {
    
    private static int index = 0;

    public static Map<String, MenuItem> menuItems(String filename) {
        Map<String, MenuItem> menu = new HashMap<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String itemName = parts[0];
                    String itemPriceString = parts[1];
                    String itemBranch = parts[2];
                    String itemType = parts[3]; 
        
                    double itemPrice = Double.parseDouble(itemPriceString); // Convert itemPriceString to double itemPrice
    
                    // menu.put(index, new MenuItem(itemName, itemPrice));
                }
                index++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return menu;
    }
}
