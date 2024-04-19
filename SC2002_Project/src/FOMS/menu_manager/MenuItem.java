package FOMS.menu_manager;

public abstract class MenuItem {
    private String item;
    private double cost;

    public MenuItem(String item, double cost) {
        this.item = item;
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public double getCost() {
        return cost;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
