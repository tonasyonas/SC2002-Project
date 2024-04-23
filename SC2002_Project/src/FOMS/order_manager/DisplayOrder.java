package FOMS.order_manager;

import java.io.IOException;
import java.util.List;

import FOMS.process_manager.ReadOrderList;

public class DisplayOrder {
    public static List<Order> processOrderID(String orderIdToFind, String status) {
        String filename = "SC2002_Project/src/FOMS/order_manager/order.txt";
        try {
            List<Order> ordersList = ReadOrderList.readOrdersFromFile(filename);
            for (Order order : ordersList) {
                if (order.getOrderId().equals(orderIdToFind)) {
                    order.setStatus(status); 
                    saveOrderListToFile(ordersList, filename); // Save the updated order list to file
                    return ordersList; // Found the order with the specified ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return null; // Order with the specified ID not found
    }
}
