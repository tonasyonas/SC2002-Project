package FOMS.account_manager;

import FOMS.FOMS_entity.*;

import java.util.List;

public interface StaffFilter {
    List<UserCredentials> filter(List<UserCredentials> staff);
}
