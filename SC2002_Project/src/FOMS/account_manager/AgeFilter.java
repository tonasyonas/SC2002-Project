package FOMS.account_manager;

import FOMS.FOMS_entity.*;
import java.util.List;
import java.util.stream.Collectors;

public class AgeFilter implements StaffFilter {
    private int age;

    public AgeFilter(int age) {
        this.age = age;
    }

    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getAge() == age)
                    .collect(Collectors.toList());
    }
}
