package FOMS.branch_manager;

import FOMS.menu_manager.Menu;
import FOMS.FOMS_entity.Staff;
import FOMS.menu_manager.MenuItem;

import java.util.ArrayList;
public class Branch {

    private final String branchName;

    private final String branchID;
    private Menu menu;
    private ArrayList<Staff> staffList;

    public Branch(String name, String id, int noOfStaff) {
        this.branchName = name;
        this.branchID = id;
        this.staffList = new ArrayList<>();
        this.menu = new Menu();
    }

    public void addStaff(Staff newStaff) {
        this.staffList.add(newStaff);
    }

    public void addMenuItem(MenuItem item) {
        this.menu.addItem(item);
    }

    public String getBranchName() {
        return this.branchName;
    }

}
