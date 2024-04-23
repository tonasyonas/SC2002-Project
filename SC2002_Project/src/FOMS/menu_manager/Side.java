package FOMS.menu_manager;

public class Side extends MenuItem {

    public Side(String item, double cost, String branch) {
        super(item, cost, branch);
    }
    public Side(String item, double cost, String branch, boolean available) {
        super(item, cost, branch, available);
    }
}
