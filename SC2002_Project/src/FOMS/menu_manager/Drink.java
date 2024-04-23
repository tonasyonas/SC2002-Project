package FOMS.menu_manager;

public class Drink extends MenuItem {

    public Drink(String item, double cost, String branch) {
        super(item, cost, branch);
    }
    public Drink(String item, double cost, String branch, boolean available) {
        super(item, cost, branch, available);
    }
}
