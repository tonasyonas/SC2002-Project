package FOMS.process_manager;

import FOMS.order_manager.Order;
import FOMS.order_manager.OrderStatus;

/**
 * The {@code StatusOrderFilter} class implements the {@code IOrderFilter} interface
 * to provide filtering of orders based on their status. It allows for operations
 * on a collection of orders to only concern those that match the given status.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class StatusOrderFilter implements IOrderFilter {
    private OrderStatus statusToFilter;

    /**
     * Constructs a {@code StatusOrderFilter} with the specified order status to filter.
     *
     * @param statusToFilter The {@code OrderStatus} used as the filter criterion.
     */

    public StatusOrderFilter(OrderStatus statusToFilter) {
        this.statusToFilter = statusToFilter;
    }

    /**
     * Tests whether the given {@code Order} object's status matches the filter criterion.
     *
     * @param order The {@code Order} object to be tested against the filter criterion.
     * @return {@code true} if the {@code Order}'s status matches the filter criterion; {@code false} otherwise.
     */
    @Override
    public boolean matches(Order order) {
         return order.getStatus().equalsIgnoreCase(statusToFilter.toString());
    }
}
