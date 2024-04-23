package FOMS.menu_manager;

import java.util.HashMap;
import java.util.Map;

public class MenuItem {
    private String item;
    private double cost;
    private String branch; // Attribute to store branch information
    private String customizations;  // Customizations as a simple string
    private boolean available;

    public MenuItem(String item, double cost, String branch, boolean available) {
        this.item = item;
        this.cost = cost;
        this.available = available;
        this.branch = branch; // Initialize the branch
        this.customizations = "";  // Initialize as empty string
    }

    public MenuItem(String item, double cost, String branch) {
        this.item = item;
        this.cost = cost;
        this.branch = branch; // Initialize the branch
        this.customizations = "";  // Initialize as empty string
    }

    public String getItem() {
        return item;
    }

    public double getCost() {
        return cost;
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
    
    public String getCustomizations() {
        return customizations;
    }

    public void setCustomizations(String customizations) {
        this.customizations = customizations;
    }
    
    // You might also want to add methods to append or clear customizations
    public void addCustomization(String customization) {
        if (!this.customizations.isEmpty()) {
            this.customizations += ", ";
        }
        this.customizations += customization;
    }

    public void clearCustomizations() {
        this.customizations = "";
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
