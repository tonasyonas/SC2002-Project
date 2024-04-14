package FOMS.branch_manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import FOMS.FOMS_entity.*;
import FOMS.menu_manager.*;

public class Restaurant {
    private static final String BRANCH_FILE = "SC2002_Project/src/FOMS/branch_manager/branch_list.txt";
    private static final String STAFF_FILE = "SC2002_Project/src/FOMS/account_manager/staff_list.txt";
    private static final String MENU_FILE = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    HashMap<String, Branch> branches;

    ArrayList<Staff> staffList;

    public Restaurant() {
        branches = new HashMap<String, Branch>();
        getBranches();
        staffList = new ArrayList<>();
        getStaff();
        getMenuItems();
    }

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
                    String gender = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    String branch = parts[5];
                    String salt = parts[6];
                    String hashedPassword = parts[7];
                    boolean needsPasswordReset = Boolean.parseBoolean(parts[8]); // Read needsPasswordReset from file
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
                        newItem = new Burger(item, price);
                    } else if (type.equals("drink")) {
                        newItem = new Drink(item, price);
                    } else if (type.equals("side")) {
                        newItem = new Side(item, price);
                    } else if (type.equals("set meal")) {
                        newItem = new SetMeal(item, price);
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