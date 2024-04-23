package FOMS.process_manager;

import FOMS.order_manager.Order;
import FOMS.order_manager.OrderStatus;

public class StatusOrderFilter implements IOrderFilter {
    private OrderStatus statusToFilter;

    public StatusOrderFilter(OrderStatus statusToFilter) {
        this.statusToFilter = statusToFilter;
    }

    @Override
    public boolean matches(Order order) {
        // Assuming order.getStatus() returns a String, we convert statusToFilter to string
        // Alternatively, if order.getStatus() returns an OrderStatus, compare the enums directly
        return order.getStatus().equalsIgnoreCase(statusToFilter.toString());
    }
}
