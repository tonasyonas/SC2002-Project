package FOMS.menu_manager;

/**
 * A class representing a burger item in the menu.
 * Inherits from the MenuItem class.
 *
 * The Burger class provides constructors to create burger items with different parameters.
 * It inherits attributes and methods from the MenuItem class.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class Burger extends MenuItem{

    /**
     * Constructs a burger item with the specified name, cost, and branch.
     *
     * @param item   The name of the burger item.
     * @param cost   The cost of the burger item.
     * @param branch The branch where the burger item is available.
     */
    public Burger(String item, double cost, String branch) {
        super(item, cost, branch);
    }

    /**
     * Constructs a burger item with the specified name, cost, branch, availability, and description.
     *
     * @param item        The name of the burger item.
     * @param cost        The cost of the burger item.
     * @param branch      The branch where the burger item is available.
     * @param available   The availability status of the burger item.
     * @param description The description of the burger item.
     */
    public Burger(String item, double cost, String branch, boolean available, String description) {
        super(item, cost, branch, available, description);
    }
}
