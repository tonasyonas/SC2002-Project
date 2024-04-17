package FOMS.account_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStaffList {
    private Map<String, UserCredentials> credentialsMap;

    public ViewStaffList() {
        this.credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
    }

    public void displayAllStaff() {
        // Convert the map values to a list for easier iteration
        List<UserCredentials> allStaff = new ArrayList<>(credentialsMap.values());

        // Display all staff details
        if (allStaff.isEmpty()) {
            System.out.println("No staff members are currently registered.");
        } else {
            for (UserCredentials staff : allStaff) {
                System.out.println("Name: " + staff.getName() +
                                   ", Role: " + staff.getRole() +
                                   ", Gender: " + staff.getGender() +
                                   ", Age: " + staff.getAge() +
                                   ", Branch: " + staff.getBranch());
            }
        }
    }

    public static void main(String[] args) {
        ViewStaffList viewStaffList = new ViewStaffList();
        viewStaffList.displayAllStaff();
    }
}

