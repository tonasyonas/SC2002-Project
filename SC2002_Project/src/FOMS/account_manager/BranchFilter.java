/**
 * The BranchFilter class implements the IStaffFilter interface to filter a list of UserCredentials based on branch.
 * It filters the list to include only those UserCredentials objects that belong to the specified branch.
 */
package FOMS.account_manager;

import java.util.List;
import java.util.stream.Collectors;

    /**
     * Filters UserCredentials based on branch.
     * 
     * This class implements the IStaffFilter interface and provides functionality to filter a list of UserCredentials
     * objects based on their branch. It returns a filtered list containing only the UserCredentials objects belonging
     * to the specified branch.
     * 
     * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
     * @version 1.0
     * @since 2024-04-24
     */

public class BranchFilter implements IStaffFilter {
    private String branch;

    /**
     * Constructs a new BranchFilter object with the specified branch.
     * @param branch The branch to filter by.
     */
    public BranchFilter(String branch) {
        this.branch = branch;
    }

    /**
     * Filters a list of UserCredentials to include only those belonging to the specified branch.
     * @param staff The list of UserCredentials to filter.
     * @return A filtered list containing only UserCredentials objects belonging to the specified branch.
     */
    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getBranch().equals(branch))
                    .collect(Collectors.toList());
    }
}
