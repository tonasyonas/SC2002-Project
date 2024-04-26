package FOMS.order_manager;
import FOMS.menu_manager.*;

import java.util.Objects;

/**
 * Represents an item within an order in FOMS. Each {@code OrderItem} encapsulates
 * a {@code MenuItem}, the quantity of that item, and any special customizations requested by the customer.
 *
 * <p>This class is essential for managing individual items in an order, allowing detailed tracking of each item's
 * specifications, which supports accurate order fulfillment.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String customization;
    
     /**
     * Constructs a new {@code OrderItem} with the specified {@code MenuItem} and quantity.
     * 
     * @param menuItem The {@code MenuItem} associated with this order item.
     * @param quantity The number of units of this item.
     */
    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customization = customization;
    }
    
    /**
     * Constructs a new {@code OrderItem} with the specified {@code MenuItem}, quantity, and customization.
     *
     * @param menuItem The {@code MenuItem} associated with this order item.
     * @param quantity The number of units of this item.
     * @param customization Any special instructions or modifications requested for this item.
     */
    public OrderItem(MenuItem menuItem, int quantity, String customization){
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customization = customization;
    }

    /**
     * Returns the {@code MenuItem} associated with this order item.
     *
     * @return The {@code MenuItem} object.
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Sets the {@code MenuItem} for this order item.
     *
     * @param menuItem The new {@code MenuItem} to associate with this item.
     */
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * Returns the quantity of this order item.
     *
     * @return The quantity as an integer.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of this order item.
     *
     * @param quantity The new quantity to set for this item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns any customizations for this order item.
     *
     * @return A string containing the customizations.
     */
    public String getCustomization() {
        return customization;
    }

    /**
     * Sets the customizations for this order item.
     *
     * @param customization A string containing the new customizations.
     */
    public void setCustomization(String customization) {
        this.customization = customization;
    }

    /**
     * Compares this {@code OrderItem} with another object for equality. Two {@code OrderItem} objects
     * are considered equal if they have the same {@code MenuItem} and customization.
     *
     * @param obj The object to compare this {@code OrderItem} against.
     * @return true if the given object represents an {@code OrderItem} equivalent to this instance, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderItem that = (OrderItem) obj;
        return Objects.equals(getMenuItem(), that.getMenuItem()) &&
               Objects.equals(getCustomization(), that.getCustomization());
    }

    /**
     * Returns a hash code for this {@code OrderItem}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMenuItem(), getCustomization());
    }


}
