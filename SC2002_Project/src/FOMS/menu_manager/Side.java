package FOMS.menu_manager;

/**
 * The Side class represents a side item on the menu.
 * It extends the MenuItem class and inherits attributes such as item name, cost, branch, availability, and description.
 * Side objects can be created with or without specifying availability and description.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class Side extends MenuItem {

    /**
     * Constructs a Side object with the specified item name, cost, and branch.
     * The availability is set to true by default, and the description is set to a default value.
     * 
     * @param item The name of the side item.
     * @param cost The cost of the side item.
     * @param branch The branch at which the side item is available.
     */
    public Side(String item, double cost, String branch) {
        super(item, cost, branch);
    }

    /**
     * Constructs a Side object with the specified item name, cost, branch, availability, and description.
     * 
     * @param item The name of the side item.
     * @param cost The cost of the side item.
     * @param branch The branch at which the side item is available.
     * @param available The availability status of the side item.
     * @param description The description of the side item.
     */
    public Side(String item, double cost, String branch, boolean available, String description) {
        super(item, cost, branch, available, description);
    }
}
