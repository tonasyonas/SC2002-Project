package FOMS.account_manager;
import FOMS.FOMS_entity.*;

import java.util.Map;

public interface IStaffListEditor {
    void EditStaffList(String filename, Map<String, UserCredentials> credentials);
}
