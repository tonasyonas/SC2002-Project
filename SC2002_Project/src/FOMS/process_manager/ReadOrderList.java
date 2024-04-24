package FOMS.process_manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import FOMS.order_manager.*;
import FOMS.menu_manager.*;

/**
 * The {@code ReadOrderList} class contains utility methods to read and parse orders from a file.
 * It provides functionality to convert each line of the file into an {@code Order} object
 * and collate all orders into a list.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24 
 */
public class ReadOrderList {
    /**
     * Reads orders from a specified file and constructs a list of {@code Order} objects.
     * 
     * @param filename The path of the file from which to read the orders.
     * @return A list of {@code Order} objects constructed from the file contents.
     * @throws IOException If an I/O error occurs during reading the file.
     */

    public static List<Order> readOrdersFromFile(String filename) throws IOException {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order order = parseOrder(line);
                if (order != null) {
                    orders.add(order);
                }
            }
        }

        return orders;
    }

    /**
     * Parses a line from the order file into an {@code Order} object.
     * The line should contain order details separated by semicolons and, for items, by commas.
     * 
     * @param line The string line from the order file to parse into an order.
     * @return The {@code Order} object constructed from the line, or {@code null} if the line cannot be parsed.
     */
    public static Order parseOrder(String line) {
        String[] parts = line.split(";");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        String orderId = parts[0];
        String status = parts[1];
        double total = Double.parseDouble(parts[2]);
        String orderType = parts[3];
        String branch = parts[4];
        List<OrderItem> items = new ArrayList<>();
    
    
        if (parts.length > 5) {
            for (int i = 5; i < parts.length; i++) {
                String[] itemParts = parts[i].split(",");
                String item = itemParts[0];
                int quantity = Integer.parseInt(itemParts[1].trim());
                double price = Double.parseDouble(itemParts[2].trim());
                MenuItem menuItem = new MenuItem(item, price, branch);
  
                if (itemParts.length > 3) {
                    String customization = itemParts[3];
                    OrderItem orderItem = new OrderItem(menuItem, quantity, customization);
                    items.add(orderItem);
                }
                else {
                    OrderItem orderItem = new OrderItem(menuItem, quantity);
                    items.add(orderItem);
                   
                }
              
            }
        }
        
        return new Order(orderId, status, total, orderType, items);
    }
    
    
}

