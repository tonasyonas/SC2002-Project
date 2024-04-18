package FOMS.account_manager;

import FOMS.FOMS_entity.*;;



public class UserCredentials {
    String name;
    String role;
    Staff.Gender gender;
    int age;
    String branch;
    String salt;
    String hashedPassword;
    boolean needsPasswordReset;

    public UserCredentials(String name, String role, Staff.Gender gender, int age, String branch,
                           String salt, String hashedPassword, boolean needsPasswordReset) {
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
        this.needsPasswordReset = needsPasswordReset;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Staff.Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getBranch() {
        return branch;
    }

    public String getSalt() {
        return salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean getNeedsPasswordReset() {
        return needsPasswordReset;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setGender(Staff.Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setNeedsPasswordReset(boolean needsPasswordReset) {
        this.needsPasswordReset = needsPasswordReset;
    }

}
