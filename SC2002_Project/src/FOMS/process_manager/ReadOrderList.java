package FOMS.process_manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import FOMS.order_manager.*;
import FOMS.menu_manager.*;

public class ReadOrderList {
    

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
    
        System.out.println("Order ID: " + orderId);
        System.out.println("Status: " + status);
        System.out.println("Total: " + String.format("%.2f", total));
        System.out.println("Type: " + orderType);
    
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
                    System.out.println(quantity + " x " + item + " Price: " + String.format("%.2f", price) + customization);
                }
                else {
                    OrderItem orderItem = new OrderItem(menuItem, quantity);
                    items.add(orderItem);
                    System.out.println(quantity + " x " + item + " Price: " + String.format("%.2f", price) + " No Customization");
                }
            

            
              
            }
        }
        
        return new Order(orderId, status, total, orderType, items);
    }
    
    
}

