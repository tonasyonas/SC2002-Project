// package FOMS.branch_manager;

// import FOMS.FOMS_entity.Staff;
// import FOMS.menu_manager.Menu;
// import FOMS.menu_manager.MenuItem;

// import java.util.ArrayList;

// /**
//  * Represents a branch in the food ordering management system.
//  *
//  * @author Kellie Tan, Jonas Tua, Sailesh Sampath, Jowee Ang, Donovan Goh
//  * @version 1.0
//  * @since 2024-04-20
//  */
// public class Branch2 {

//     private final String branchName; // The name of the branch
//     private final String branchID; // The ID of the branch
//     private Menu menu; // The menu associated with the branch
//     private ArrayList<Staff> staffList; // The list of staff working at the branch

//     /**
//      * Constructs a new branch with the specified ID, name, and initial number of staff.
//      *
//      * @param id         the ID of the branch
//      * @param name       the name of the branch
//      * @param noOfStaff  the initial number of staff at the branch
//      */
//     public Branch(String id, String name, int noOfStaff) {
//         this.branchName = name;
//         this.branchID = id;
//         this.staffList = new ArrayList<>();
//         this.menu = new Menu();
//     }

//     /**
//      * Adds a staff member to the branch's staff list.
//      *
//      * @param newStaff the staff member to add
//      */
//     public void addStaff(Staff newStaff) {
//         this.staffList.add(newStaff);
//     }

//     /**
//      * Adds a menu item to the branch's menu.
//      *
//      * @param item the menu item to add
//      */
//     public void addMenuItem(MenuItem item) {
//         this.menu.addItem(item);
//     }

//     /**
//      * Gets the name of the branch.
//      *
//      * @return the name of the branch
//      */
//     public String getBranchName() {
//         return this.branchName;
//     }

//     /**
//      * Gets the ID of the branch.
//      *
//      * @return the ID of the branch
//      */
//     public String getBranchID() {
//         return this.branchID;
//     }

// }
