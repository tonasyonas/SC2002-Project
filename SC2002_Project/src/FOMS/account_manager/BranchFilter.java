package FOMS.account_manager;

import java.util.List;
import java.util.stream.Collectors;

public class BranchFilter implements IStaffFilter{
    private String branch;

    public BranchFilter(String branch) {
        this.branch = branch;
    }

    @Override
    public List<UserCredentials> filter(List<UserCredentials> staff) {
        return staff.stream()
                    .filter(s -> s.getBranch().equals(branch))
                    .collect(Collectors.toList());
    }
}
