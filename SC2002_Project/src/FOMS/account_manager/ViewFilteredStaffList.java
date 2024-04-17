package FOMS.account_manager;

import FOMS.FOMS_entity.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class ViewFilteredStaffList {
    private Map<String, UserCredentials> credentialsMap;

    public ViewFilteredStaffList(Map<String, UserCredentials> credentialsMap) {
        this.credentialsMap = credentialsMap;
    }

    public void filterStaff() {
        Scanner scanner = new Scanner(System.in);
        List<UserCredentials> credentialsList = new ArrayList<>(credentialsMap.values());
        List<UserCredentials> filteredList;

        System.out.println("Filter by:\n1. Branch\n2. Role\n3. Gender\n4. Age");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter Branch: ");
                String branch = scanner.next();
                filteredList = StaffFilter.filterByBranch(credentialsList, branch);
                break;
            case 2:
                System.out.print("Enter Role: ");
                String role = scanner.next();
                filteredList = StaffFilter.filterByRole(credentialsList, role);
                break;
            case 3:
                System.out.print("Enter Gender (M/F): ");
                String genderInput = scanner.next();
                Staff.Gender gender = "M".equalsIgnoreCase(genderInput) ? Staff.Gender.M : Staff.Gender.F;
                filteredList = StaffFilter.filterByGender(credentialsList, gender);
                break;
            case 4:
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                filteredList = StaffFilter.filterByAge(credentialsList, age);
                break;
            default:
                System.out.println("No valid filter selected. Showing all staff.");
                filteredList = credentialsList;
                break;
        }

        displayStaff(filteredList);
        scanner.close();
    }

    private void displayStaff(List<UserCredentials> staffList) {
        if (staffList.isEmpty()) {
            System.out.println("No staff members match the criteria.");
        } else {
            for (UserCredentials credentials : staffList) {
                System.out.println("Name: " + credentials.getName() +
                                   ", Role: " + credentials.getRole() +
                                   ", Gender: " + credentials.getGender() +
                                   ", Age: " + credentials.getAge() +
                                   ", Branch: " + credentials.getBranch());
            }
        }
    }

    // Method to be called from elsewhere in your application
    public static void main(String[] args) {
        // Assume a method exists that retrieves the staff credentials
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
        ViewFilteredStaffList viewer = new ViewFilteredStaffList(credentialsMap);
        viewer.filterStaff();
    }
}
