package FOMS.branch_manager;

import FOMS.FOMS_entity.Staff;
import FOMS.menu_manager.Menu;
import FOMS.menu_manager.MenuItem;

import java.util.ArrayList;

/**
 * The Branch class represents a branch in the FOMS system.
 * It contains information about the branch name, branch ID, staff list, and menu.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class Branch {

    private final String branchName;

    private final String branchID;
    private Menu menu;
    private ArrayList<Staff> staffList;

    /**
     * Constructs a Branch object with the specified ID, name, and initial number of staff.
     * 
     * @param id The ID of the branch.
     * @param name The name of the branch.
     * @param noOfStaff The initial number of staff members in the branch.
     */
    public Branch(String id, String name, int noOfStaff) {
        this.branchName = name;
        this.branchID = id;
        this.staffList = new ArrayList<>();
        this.menu = new Menu();
    }

    /**
     * Adds a new staff member to the branch.
     * 
     * @param newStaff The staff member to add.
     */
    public void addStaff(Staff newStaff) {
        this.staffList.add(newStaff);
    }

    /**
     * Adds a new menu item to the branch's menu.
     * 
     * @param item The menu item to add.
     */
    public void addMenuItem(MenuItem item) {
        this.menu.addItem(item);
    }

    /**
     * Retrieves the name of the branch.
     * 
     * @return The name of the branch.
     */
    public String getBranchName() {
        return this.branchName;
    }

    /**
     * Retrieves the ID of the branch.
     * 
     * @return The ID of the branch.
     */
    public String getBranchID() {
        return this.branchID;
    }

}
