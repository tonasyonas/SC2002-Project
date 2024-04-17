package FOMS.account_manager;

import FOMS.FOMS_entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStaffList {
        // Assume you have a method to get the credentials map
        Map<String, UserCredentials> credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");

        // Create arrays to store details
        String[] names = new String[credentialsMap.size()];
        String[] roles = new String[credentialsMap.size()];
        Staff.Gender[] genders = new Staff.Gender[credentialsMap.size()];
        int[] ages = new int[credentialsMap.size()];
        String[] branches = new String[credentialsMap.size()];
        // Convert Map to List for easier manipulation and display
        List<UserCredentials> credentialsList = new ArrayList<>(credentialsMap.values());

<<<<<<< HEAD
        System.out.println("Filter by:\n1. Branch\n2. Role\n3. Gender\n4. Age");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        
        StaffFilter filter;

        switch (choice) {
            case 1:
                System.out.print("Enter Branch: ");
                String branch = scanner.next();
                filter = new BranchFilter(branch);
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
                System.out.print("Enter minimum age: ");
                int minAge = scanner.nextInt();
                System.out.print("Enter maximum age: ");
                int maxAge = scanner.nextInt();
                filteredList = StaffFilter.filterByAge(credentialsList, minAge, maxAge);
                break;
            default:
                System.out.println("Invalid option. Displaying all staff.");
                filteredList = credentialsList;
                break;
        }

        List<UserCredentials> filteredList = filter.filter(credentialsList);
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
                System.out.println();
            }
=======
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
        // Display staff details
        for (UserCredentials credentials : credentialsList) {
            System.out.println("Name: " + credentials.getName());
            System.out.println("Role: " + credentials.getRole());
            System.out.println("Gender: " + credentials.getGender());
            System.out.println("Age: " + credentials.getAge());
            System.out.println("Branch: " + credentials.getBranch());
            System.out.println();
>>>>>>> 177eb4104106e969d5227a0d6a5333b10c25d360
        }
    }
