package FOMS.menu_manager;



/**
 * Represents a menu item.
 * Each menu item has a name, cost, branch, customizations, availability, and description.
 * 
 * Customizations are represented as a string containing additional details or modifications to the item.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class MenuItem {
    private String item;
    private double cost;
    private String branch; 
    private String customizations;  
    private boolean available;
    private String description;

    /**
     * Constructs a MenuItem with the specified attributes.
     * 
     * @param item The name of the menu item.
     * @param cost The cost of the menu item.
     * @param branch The branch to which the menu item belongs.
     * @param available The availability of the menu item.
     * @param description The description of the menu item.
     */
    public MenuItem(String item, double cost, String branch, boolean available, String description) {
        this.item = item;
        this.cost = cost;
        this.available = available;
        this.branch = branch; 
        this.description = description;
        this.customizations = ""; 
    }

    /**
     * Constructs a MenuItem with the specified attributes.
     * 
     * @param item The name of the menu item.
     * @param cost The cost of the menu item.
     * @param branch The branch to which the menu item belongs.
     */
    public MenuItem(String item, double cost, String branch) {
        this.item = item;
        this.cost = cost;
        this.branch = branch; 
        this.customizations = "";  
    }

    /**
     * Gets the name of the menu item.
     * 
     * @return The name of the menu item.
     */
    public String getItem() {
        return item;
    }

    /**
     * Gets the cost of the menu item.
     * 
     * @return The cost of the menu item.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the branch to which the menu item belongs.
     * 
     * @return The branch of the menu item.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the branch to which the menu item belongs.
     * 
     * @param branch The branch of the menu item.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Sets the name of the menu item.
     * 
     * @param item The name of the menu item.
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Sets the cost of the menu item.
     * 
     * @param cost The cost of the menu item.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    /**
     * Gets the customizations for the menu item.
     * 
     * @return The customizations for the menu item.
     */
    public String getCustomizations() {
        return customizations;
    }

    /**
     * Sets the customizations for the menu item.
     * 
     * @param customizations The customizations for the menu item.
     */
    public void setCustomizations(String customizations) {
        this.customizations = customizations;
    }
    

    /**
     * Adds a customization to the menu item.
     * 
     * @param customization The customization to add.
     */
    public void addCustomization(String customization) {
        if (!this.customizations.isEmpty()) {
            this.customizations += ", ";
        }
        this.customizations += customization;
    }

    /**
     * Clears the customizations for the menu item.
     */
    public void clearCustomizations() {
        this.customizations = "";
    }

    /**
     * Checks if the menu item is available.
     * 
     * @return true if the menu item is available, false otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the menu item.
     * 
     * @param available The availability of the menu item.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    /**
     * Gets the description of the menu item.
     * 
     * @return The description of the menu item.
     */
    public String getDescription() {
        return description;
    }
    
}
