/**
 * The AgeFilter class implements the IStaffFilter interface to filter staff members based on their age.
 * It filters a list of UserCredentials objects and returns only those with the specified age.
 */
package FOMS.account_manager;

import java.util.List;
import java.util.stream.Collectors;

public class AgeFilter implements IStaffFilter {
    /**
     * The age to filter the staff members by.
     */
    private int age;

    /**
     * Constructs a new AgeFilter object with the specified age.
     * @param age The age to filter the staff members by.
     */
    public AgeFilter(int age) {
        this.age = age;
    }

    /**
     * Filters a list of UserCredentials objects based on their age.
     * @param staff The list of UserCredentials objects to filter.
     * @return A filtered list containing only the UserCredentials objects with the specified age.
     */
    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getAge() == age)
                    .collect(Collectors.toList());
    }
}
