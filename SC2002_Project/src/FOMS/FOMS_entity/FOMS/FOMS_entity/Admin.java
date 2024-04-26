package FOMS.FOMS_entity;


public class Admin extends Staff {
    public Admin(String name, String loginID, Gender gender, int age, String salt, String hashedPassword, boolean needsPasswordReset) {
        super(name, loginID, gender, age, salt, hashedPassword, needsPasswordReset);
    }


}
