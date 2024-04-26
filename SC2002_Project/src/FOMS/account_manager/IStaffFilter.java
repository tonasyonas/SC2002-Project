package FOMS.account_manager;

import java.util.List;

/**
 * The IStaffFilter interface defines a contract for filtering a list of UserCredentials objects.
 * Implementing classes should provide functionality to filter the list based on specific criteria.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface IStaffFilter {
    /**
     * Filters a list of UserCredentials objects based on specific criteria.
     * 
     * @param staff The list of UserCredentials to filter.
     * @return A list containing only the UserCredentials objects that meet the filtering criteria.
     */
    List<UserCredentials> filter(List<UserCredentials> staff);
}
