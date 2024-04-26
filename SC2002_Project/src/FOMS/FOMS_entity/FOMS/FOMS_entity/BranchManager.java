package FOMS.FOMS_entity;

public class BranchManager extends Staff {
    private String branch;

    public BranchManager(String name, String loginID, Gender gender, int age, String branch,
                         String salt, String hashedPassword, boolean needsPasswordReset) {
        super(name, loginID, gender, age, salt, hashedPassword, needsPasswordReset);
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
