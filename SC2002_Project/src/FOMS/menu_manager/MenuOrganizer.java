package FOMS.menu_manager;
import java.util.Map;
import java.util.HashMap;

public class MenuOrganizer {
    private Map<String, MenuItem> menuMap;
    private Map<String, Map<String, MenuItem>> branchMenus;

    public MenuOrganizer(Map<String, MenuItem> menuMap) {
        this.menuMap = menuMap;
        this.branchMenus = new HashMap<>();
        organizeByBranch();
    }

    private void organizeByBranch() {
        for (MenuItem item : menuMap.values()) {
            String branch = item.getBranch();
            branchMenus.computeIfAbsent(branch, k -> new HashMap<>()).put(item.getItem(), item);
        }
    }

    public Map<String, MenuItem> getMenuForBranch(String branch) {
        return branchMenus.get(branch);
    }

}
