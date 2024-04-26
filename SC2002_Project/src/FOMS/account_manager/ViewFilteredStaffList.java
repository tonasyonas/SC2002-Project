package FOMS.account_manager;

import FOMS.FOMS_entity.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The ViewFilteredStaffList class provides functionality to filter and display staff members based on various criteria.
 * It allows filtering by branch, role, gender, and age.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class ViewFilteredStaffList {
    private Map<String, UserCredentials> credentialsMap;

    /**
     * Constructs a new ViewFilteredStaffList instance with the specified credentials map.
     * 
     * @param credentialsMap The map containing user credentials.
     */
    public ViewFilteredStaffList(Map<String, UserCredentials> credentialsMap) {
        this.credentialsMap = credentialsMap;
    }

    /**
     * Filters and displays staff members based on user input criteria.
     * 
     * @param scanner The Scanner object for user input.
     */
    public void filterStaff(Scanner scanner) {  
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
                filter = staff -> staff;  
                break;
        }
        
        filteredList = filter.filter(credentialsList);
        displayStaff(filteredList);
    }

    /**
     * Displays the staff list with their details.
     * 
     * @param staffList The list of staff members to display.
     * @return The count of staff members displayed.
     */
    public static int displayStaff(List<UserCredentials> staffList) {
        int count = 0;
        if (staffList.isEmpty()) {
            System.out.println("No staff members match the criteria.");
        } else {
            for (UserCredentials credentials : staffList) {
                System.out.println("Name: " + credentials.getName() +
                                   ", Role: " + credentials.getRole() +
                                   ", Gender: " + credentials.getGender() +
                                   ", Age: " + credentials.getAge() +
                                   ", Branch: " + credentials.getBranch());
                count ++;

            }
        }
        return count;
    }
}
