package FOMS.account_manager;

import java.util.Map;

public interface IStaffListEditor {
    void EditStaffList(String filename, Map<String, UserCredentials> credentials);
}
