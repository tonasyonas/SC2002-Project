package FOMS.menu_manager;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuItem {
    private String item;
    private double cost;
    private String branch; // Attribute to store branch information
    private Map<String, Double> customizations;

    public MenuItem(String item, double cost, String branch) {
        this.item = item;
        this.cost = cost;
        this.branch = branch; // Initialize the branch
        this.customizations = new HashMap<>(); // Initialize the customizations map

    }

    public String getItem() {
        return item;
    }

    public double getCost() {
        double totalCost = cost;
        for (double additionalCost : customizations.values()) {
            totalCost += additionalCost;
        }
        return totalCost;
    }
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // Method to add a customization with its additional cost
    public void addCustomization(String customization, double additionalCost) {
        customizations.put(customization, additionalCost);
    }
    
    // Method to remove a customization
    public void removeCustomization(String customization) {
        customizations.remove(customization);
    }
    
    // Method to get the customizations map
    public Map<String, Double> getCustomizations() {
        return customizations;
    }
    
}
