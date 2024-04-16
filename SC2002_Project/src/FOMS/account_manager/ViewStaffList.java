package FOMS.account_manager;

import FOMS.FOMS_entity.*;

import java.util.Map;

public class ViewStaffList {
    public void viewStaffList() {
        // Assume you have a method to get the credentials map
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");

        // Create arrays to store details
        String[] names = new String[credentialsMap.size()];
        String[] roles = new String[credentialsMap.size()];
        Staff.Gender[] genders = new Staff.Gender[credentialsMap.size()];
        int[] ages = new int[credentialsMap.size()];
        String[] branches = new String[credentialsMap.size()];

        // Iterate over each credential in the map and store details in arrays
        int index = 0;
        for (Map.Entry<String, UserCredentials> entry : credentialsMap.entrySet()) {
            UserCredentials credentials = entry.getValue();

            // Extract details
            names[index] = credentials.getName();
            roles[index] = credentials.getRole();
            genders[index] = credentials.getGender();
            ages[index] = credentials.getAge();
            branches[index] = credentials.getBranch();

            index++;
        }

        // Now you have arrays containing the details
        // You can use these arrays as needed
        // For example, you can print them out
        for (int i = 0; i < credentialsMap.size(); i++) {
            System.out.println("Name: " + names[i]);
            System.out.println("Role: " + roles[i]);
            System.out.println("Gender: " + genders[i]);
            System.out.println("Age: " + ages[i]);
            System.out.println("Branch: " + branches[i]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ViewStaffList view = new ViewStaffList();
        view.viewStaffList();
    }
}
