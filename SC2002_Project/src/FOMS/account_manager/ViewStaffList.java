package FOMS.account_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The ViewStaffList class provides functionality to display all staff members.
 * It retrieves staff credentials from a file and displays their details.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class ViewStaffList {
    private Map<String, UserCredentials> credentialsMap;

    /**
     * Constructs a new ViewStaffList instance and initializes the credentials map by reading staff credentials from a file.
     */
    public ViewStaffList() {
        this.credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
    }

    /**
     * Displays details of all staff members.
     */
    public void displayAllStaff() {
        List<UserCredentials> allStaff = new ArrayList<>(credentialsMap.values());

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
}