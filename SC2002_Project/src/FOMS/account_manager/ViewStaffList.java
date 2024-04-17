package FOMS.account_manager;

import FOMS.FOMS_entity.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ViewStaffList {
    private Map<String, UserCredentials> credentialsMap;

    public ViewStaffList() {
        this.credentialsMap = ReadStaffList.getStaffCredentials("SC2002_Project/src/FOMS/account_manager/staff_list.txt");
    }

    public void viewFilteredStaff() {
        Scanner scanner = new Scanner(System.in);
        List<UserCredentials> credentialsList = new ArrayList<>(credentialsMap.values());

        System.out.println("Filter by:\n1. Branch\n2. Role\n3. Gender\n4. Age");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        IStaffFilter filter;
        switch (choice) {
            case 1:
                System.out.print("Enter Branch: ");
                String branch = scanner.next();
                filter = new BranchFilter(branch);
                break;
            case 2:
                System.out.print("Enter Role: ");
                String role = scanner.next();
                filter = new RoleFilter(role);
                break;
            case 3:
                System.out.print("Enter Gender (M/F): ");
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
        }
    }

    public static void main(String[] args) {
        ViewStaffList view = new ViewStaffList();
        view.viewFilteredStaff();
    }
}
