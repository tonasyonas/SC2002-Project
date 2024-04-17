package FOMS.account_manager;

import FOMS.FOMS_entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class StaffFilter {
    public static List<UserCredentials> filterByBranch(List<UserCredentials> staff, String branch) {
        return staff.stream()
                    .filter(s -> s.getBranch().equals(branch))
                    .collect(Collectors.toList());
    }

    public static List<UserCredentials> filterByRole(List<UserCredentials> staff, String role) {
        return staff.stream()
                    .filter(s -> s.getRole().equals(role))
                    .collect(Collectors.toList());
    }

    public static List<UserCredentials> filterByGender(List<UserCredentials> staff, Staff.Gender gender) {
        return staff.stream()
                    .filter(s -> s.getGender() == gender)
                    .collect(Collectors.toList());
    }

    public static List<UserCredentials> filterByAge(List<UserCredentials> staff, int minAge, int maxAge) {
        return staff.stream()
                    .filter(s -> s.getAge() >= minAge && s.getAge() <= maxAge)
                    .collect(Collectors.toList());
    }
}
