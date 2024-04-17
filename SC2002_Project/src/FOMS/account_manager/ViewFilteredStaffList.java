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

        IStaffFilter filter = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Branch: ");
                String branch = scanner.next();
                filter = new BranchFilter(branch);
                break;
            case 2:
                System.out.print("Enter role: ");
                String role = scanner.next();
                filter = new RoleFilter(role);
                break;
            case 3:
                System.out.print("Enter gender (M/F): ");
                String genderInput = scanner.next();
                Staff.Gender gender = "M".equalsIgnoreCase(genderInput) ? Staff.Gender.M : Staff.Gender.F;
                filter = new GenderFilter(gender);
                break;        
            case 4:
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                filter = new AgeFilter(age);
                break;
            default:
                System.out.println("Invalid option. Displaying all staff.");
                filter = staff -> staff;  // Lambda for no filtering
                break;
        }
        

        if (filter != null) {
            filteredList = filter.filter(credentialsList);
        } else {
            filteredList = credentialsList;
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
