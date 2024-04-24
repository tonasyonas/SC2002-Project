package FOMS.order_manager;

/**
 * Enumerates the possible statuses of an order.
 * Each status corresponds to a specific stage in the lifecycle of an order.
 * The OrderStatus enum provides a set of constants representing these statuses.
 *
 * The available statuses are:
 * - NEW: The order is newly placed and has not yet been processed.
 * - READY_TO_PICKUP: The order is ready for pickup by the customer.
 * - COMPLETED: The order has been successfully collected by the customer.
 * - CANCELLED: The order has been cancelled, either by the customer or due to inactivity.
 *
 * Each constant holds a string representation of the respective status.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public enum OrderStatus {
    NEW("New"), 
    READY_TO_PICKUP("Ready to Pickup"), 
    COMPLETED("Completed"), 
    CANCELLED("Cancelled");

    private final String status;

    /**
     * Constructs an OrderStatus enum constant with the specified status.
     *
     * @param status The string representation of the order status.
     */
    OrderStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the string representation of the order status.
     *
     * @return The string representation of the order status.
     */
    @Override
    public String toString() {
        return this.status;
    }
}
