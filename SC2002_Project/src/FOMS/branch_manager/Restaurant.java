package FOMS.branch_manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import FOMS.FOMS_entity.*;
import FOMS.menu_manager.*;

/**
 * The Restaurant class represents a restaurant with branches, staff, and menu items.
 * It reads branch, staff, and menu information from text files and constructs the restaurant objects.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class Restaurant {
    private static final String BRANCH_FILE = "SC2002_Project/src/FOMS/branch_manager/branch_list.txt";
    private static final String STAFF_FILE = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private static final String MENU_FILE = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    HashMap<String, Branch> branches;

    ArrayList<Staff> staffList;

    /**
     * Constructs a new Restaurant object with empty branch map and staff list, and initializes branches, staff, and menu items.
     */
    public Restaurant() {
        branches = new HashMap<String, Branch>();
        getBranches();
        staffList = new ArrayList<>();
        getStaff();
        getMenuItems();
    }

    /**
     * Reads branch information from a file and populates the branches map accordingly.
     */
    private void getBranches() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(BRANCH_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String branchID = parts[0];
                    String branchName = parts[1];
                    String staffCapacity = parts[2];
                    System.out.println("Added branch: " + branchName);
                    branches.put(branchID, new Branch(branchName, branchID, Integer.parseInt(staffCapacity)));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads staff information from a file and populates the staff list and respective branches with staff accordingly.
     */
    private void getStaff() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 9) {
                    String name = parts[0];
                    String loginID = parts[1];
                    String role = parts[2];
                    String genderStr = parts[3];
                    Staff.Gender gender; 
                    if ("F".equals(genderStr)) {
                        gender = Staff.Gender.F;
                    } else if ("M".equals(genderStr)) {
                        gender = Staff.Gender.M;
                    } else {
                        throw new IllegalArgumentException("Invalid gender value: " + genderStr);
                    }

                    int age = Integer.parseInt(parts[4]);
                    String branch = parts[5];
                    String salt = parts[6];
                    String hashedPassword = parts[7];
                    boolean needsPasswordReset = Boolean.parseBoolean(parts[8]); 
                    if (role.equals("A")) {
                        staffList.add(new Admin(name, loginID, gender, age, salt, hashedPassword, needsPasswordReset));
                        System.out.println("Added " + name + " to staffList");
                        continue;
                    }
                    Staff newStaff;
                    if (role.equals("M")) {
                        newStaff = new BranchManager(name, loginID, gender, age, branch, salt, hashedPassword, needsPasswordReset);
                    } else if (role.equals("S")) {
                        newStaff = new BranchStaff(name, loginID, gender, age, branch, salt, hashedPassword, needsPasswordReset);
                    } else {
                        continue;
                    }

                    Branch currBranch = branches.get(branch);
                    currBranch.addStaff(newStaff);
                    System.out.println("Added " + name + " to " + currBranch.getBranchName());
                    staffList.add(newStaff);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads menu item information from a file and populates the respective branches with menu items accordingly.
     */
    private void getMenuItems() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String item = parts[0];
                    Double price = Double.parseDouble(parts[1]);
                    String branch = parts[2];
                    String type = parts[3];
                    MenuItem newItem;
                    if (type.equals("burger")) {
                        newItem = new Burger(item, price, branch);
                    } else if (type.equals("drink")) {
                        newItem = new Drink(item, price, branch);
                    } else if (type.equals("side")) {
                        newItem = new Side(item, price, branch);
                    } else if (type.equals("set meal")) {
                        newItem = new SetMeal(item, price, branch);
                    } else {
                        continue;
                    }
                    branches.get(branch).addMenuItem(newItem);
                    System.out.println("Added " + item + " to " + branches.get(branch).getBranchName());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }



}