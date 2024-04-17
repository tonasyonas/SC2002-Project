package FOMS.account_manager;

import FOMS.FOMS_entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStaffList {
    public void viewStaffList() {
        // Assume you have a method to get the credentials map
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");

        // Convert Map to List for easier manipulation and display
        List<UserCredentials> credentialsList = new ArrayList<>(credentialsMap.values());

        // Display staff details
        for (UserCredentials credentials : credentialsList) {
            System.out.println("Name: " + credentials.getName());
            System.out.println("Role: " + credentials.getRole());
            System.out.println("Gender: " + credentials.getGender());
            System.out.println("Age: " + credentials.getAge());
            System.out.println("Branch: " + credentials.getBranch());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ViewStaffList view = new ViewStaffList();
        view.viewStaffList();
    }
}
