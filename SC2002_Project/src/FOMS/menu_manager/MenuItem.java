package FOMS.menu_manager;

public abstract class MenuItem {
    private String item;
    private double cost;

    public MenuItem(String item, double cost) {
        this.item = item;
        this.cost = cost;
    }
}
