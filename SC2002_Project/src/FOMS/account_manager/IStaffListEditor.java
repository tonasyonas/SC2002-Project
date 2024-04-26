package FOMS.account_manager;

import java.util.Map;

/**
 * The IStaffListEditor interface defines a contract for editing a staff list.
 * Implementing classes should provide functionality to edit the staff list stored in a file.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface IStaffListEditor {
    /**
     * Edits the staff list stored in a file.
     * 
     * @param filename The name of the file containing the staff list.
     * @param credentials A map containing the login IDs as keys and the corresponding UserCredentials objects as values.
     */
    void EditStaffList(String filename, Map<String, UserCredentials> credentials);
}
