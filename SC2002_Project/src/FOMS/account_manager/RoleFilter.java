package FOMS.account_manager;

import java.util.List;
import java.util.stream.Collectors;

public class RoleFilter implements IStaffFilter {
    private String role;

    public RoleFilter(String role) {
        this.role = role;
    }

    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getRole().equals(role))
                    .collect(Collectors.toList());
    }
}
