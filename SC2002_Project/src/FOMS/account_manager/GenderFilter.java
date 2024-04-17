package FOMS.account_manager;

import FOMS.FOMS_entity.*;
import java.util.List;
import java.util.stream.Collectors;

public class GenderFilter implements IStaffFilter {
    private Staff.Gender gender;

    public GenderFilter(Staff.Gender gender) {
        this.gender = gender;
    }

    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getGender() == gender)
                    .collect(Collectors.toList());
    }
}
