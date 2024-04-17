package FOMS.account_manager;

import java.util.List;

public interface IStaffFilter {
    List<UserCredentials> filter(List<UserCredentials> staff);
}
