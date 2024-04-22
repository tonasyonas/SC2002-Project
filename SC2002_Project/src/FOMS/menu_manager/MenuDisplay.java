package FOMS.menu_manager;
import java.util.Map;

public interface MenuDisplay {
    void displayMenu(String branchName, Map<String, MenuItem> menuItems);
}
