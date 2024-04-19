package FOMS.FOMS_entity;
// import FOMS.branch_manager.Restaurant;

public abstract class Staff {
    private String name;
    private String loginID;

    public Gender gender;
    public enum Gender{
        F,
        M
    };

    private int age;
    private String salt;
    private String hashedPassword;
    private boolean needsPasswordReset;

    public Staff(String name, String loginID, Gender gender, int age,
                           String salt, String hashedPassword, boolean needsPasswordReset) {
        this.name = name;
        this.loginID = loginID;
        this.gender = gender;
        this.age = age;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.needsPasswordReset = needsPasswordReset;
    }

    public String getName() {
        return this.name;
    }

    public boolean authenticate(String password) {
        // Hashing
        return password == this.hashedPassword;
    }

    public int getPermission() {
        return -1;
    }

    public String getStaffActions() {
        return "logout";
    }
    
    // Common functionalities that all the staff have
}
