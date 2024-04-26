package FOMS.account_manager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The RoleFilter class implements the IStaffFilter interface to filter staff members by their role.
 * 
 * It filters the list of UserCredentials based on the specified role.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class RoleFilter implements IStaffFilter {
    private String role;

    /**
     * Constructs a new RoleFilter object with the specified role.
     * 
     * @param role The role by which staff members will be filtered.
     */
    public RoleFilter(String role) {
        this.role = role;
    }

    /**
     * Filters the list of UserCredentials based on the specified role.
     * 
     * @param staff The list of UserCredentials to filter.
     * @return A filtered list containing only UserCredentials with the specified role.
     */
    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getRole().equals(role))
                    .collect(Collectors.toList());
    }
}
