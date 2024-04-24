/**
 * The GenderFilter class implements the IStaffFilter interface to filter a list of UserCredentials based on gender.
 */
package FOMS.account_manager;

import java.util.List;
import java.util.stream.Collectors;

import FOMS.FOMS_entity.*;

public class GenderFilter implements IStaffFilter {
    private Staff.Gender gender;

    /**
     * Constructs a GenderFilter object with the specified gender.
     * @param gender The gender to filter by.
     */
    public GenderFilter(Staff.Gender gender) {
        this.gender = gender;
    }

    /**
     * Filters a list of UserCredentials based on gender.
     * @param staff The list of UserCredentials to filter.
     * @return A filtered list of UserCredentials with the specified gender.
     */
    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getGender() == gender)
                    .collect(Collectors.toList());
    }
}
