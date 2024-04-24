package FOMS.process_manager;

import FOMS.order_manager.*;


/**
 * The {@code IDOrderFilter} class implements the {@code IOrderFilter} interface
 * to provide a filter for orders based on a specific order ID.
 * This allows for operations on a collection of orders that only concern the order(s)
 * with the given identifier.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */


public class IDOrderFilter implements IOrderFilter {
    private String idToFilter;

    /**
     * Constructs an {@code IDOrderFilter} with the specified order ID to filter.
     *
     * @param idToFilter The order ID used as the filter criterion.
     */

    public IDOrderFilter(String idToFilter) {
        this.idToFilter = idToFilter;
    }

    /**
     * Determines if the specified {@code Order} matches the filter criterion.
     *
     * @param order The {@code Order} to be evaluated against the filter.
     * @return {@code true} if the order ID matches the filter; {@code false} otherwise.
     */
    @Override
    public boolean matches(Order order) {
        return order.getOrderId().equals(idToFilter);
    }
}
