package FOMS.menu_manager;

public class Burger extends MenuItem{

    public Burger(String item, double cost, String branch) {
        super(item, cost, branch);
    }

    public Burger(String item, double cost, String branch, boolean available, String description) {
        super(item, cost, branch, available, description);
    }
}
