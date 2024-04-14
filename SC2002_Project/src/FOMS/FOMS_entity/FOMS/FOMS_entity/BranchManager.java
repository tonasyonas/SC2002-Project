package FOMS.FOMS_entity;

public class BranchManager extends Staff{
    private String branch;
    public BranchManager(String name, String loginID, String gender, int age, String branch,
                       String salt, String hashedPassword, boolean needsPasswordReset) {
        super(name, loginID, gender, age, salt, hashedPassword, needsPasswordReset);
        this.branch = branch;
    }
}
