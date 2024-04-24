package FOMS.process_manager;

import FOMS.order_manager.Order;
/**
 * The {@code IOrderFilter} interface defines a single method for implementing
 * order filtering logic. Classes implementing this interface can define custom
 * filter criteria to determine whether an {@code Order} object matches certain conditions.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24 
 */
public interface IOrderFilter {

    /**
     * Tests whether the given {@code Order} object matches the filter criteria
     * implemented in the class that implements this interface.
     * 
     * @param order The {@code Order} object to be tested against the filter criteria.
     * @return {@code true} if the {@code Order} matches the filter criteria; {@code false} otherwise.
     */
    boolean matches(Order order);
}
