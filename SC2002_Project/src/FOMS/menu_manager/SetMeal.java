package FOMS.menu_manager;

public class SetMeal extends MenuItem {

    public SetMeal(String item, double cost, String branch) {
        super(item, cost, branch);
    }
    public SetMeal(String item, double cost, String branch, boolean available) {
        super(item, cost, branch, available);
    }
}
