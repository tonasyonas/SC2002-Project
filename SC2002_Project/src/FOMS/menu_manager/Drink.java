package FOMS.menu_manager;

/**
 * Represents a drink item in the menu.
 * Extends the MenuItem class.
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class Drink extends MenuItem {

    /**
     * Constructs a Drink object with the specified attributes.
     *
     * @param item   The name of the drink.
     * @param cost   The cost of the drink.
     * @param branch The branch where the drink is available.
     */
    public Drink(String item, double cost, String branch) {
        super(item, cost, branch);
    }

    /**
     * Constructs a Drink object with the specified attributes.
     *
     * @param item        The name of the drink.
     * @param cost        The cost of the drink.
     * @param branch      The branch where the drink is available.
     * @param available   The availability status of the drink.
     * @param description The description of the drink.
     */
    public Drink(String item, double cost, String branch, boolean available, String description) {
        super(item, cost, branch, available, description);
    }
}
