package FOMS.process_manager;

import java.io.IOException;
import java.util.List;

import FOMS.order_manager.Order;

public abstract class DisplayOrder {

    public static void displayOrders() {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt"; // Replace this with the actual file path
    
        try {
            List<Order> orders = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : orders) {
                System.out.println(order);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


    
    

}