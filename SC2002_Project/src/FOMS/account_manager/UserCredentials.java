package FOMS.account_manager;

import FOMS.FOMS_entity.*;;


/**
 * The UserCredentials class represents the credentials of a user in the system.
 * It stores information such as name, role, gender, age, branch, salt, hashed password, and whether a password reset is needed.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class UserCredentials {
    String name;
    String role;
    Staff.Gender gender;
    int age;
    String branch;
    String salt;
    String hashedPassword;
    boolean needsPasswordReset;

    /**
     * Constructs a new UserCredentials object with the specified parameters.
     * 
     * @param name The name of the user.
     * @param role The role of the user (e.g., "S" for staff, "M" for manager).
     * @param gender The gender of the user.
     * @param age The age of the user.
     * @param branch The branch where the user belongs.
     * @param salt The salt used for hashing the password.
     * @param hashedPassword The hashed password of the user.
     * @param needsPasswordReset Indicates whether the user needs to reset their password.
     */
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

    /**
     * Retrieves the name of the user.
     * 
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the role of the user.
     * 
     * @return The role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Retrieves the gender of the user.
     * 
     * @return The gender of the user.
     */
    public Staff.Gender getGender() {
        return gender;
    }

    /**
     * Retrieves the age of the user.
     * 
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Retrieves the branch where the user belongs.
     * 
     * @return The branch where the user belongs.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Retrieves the salt used for hashing the password.
     * 
     * @return The salt used for hashing the password.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Retrieves the hashed password of the user.
     * 
     * @return The hashed password of the user.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Indicates whether the user needs to reset their password.
     * 
     * @return true if the user needs to reset their password, false otherwise.
     */
    public boolean getNeedsPasswordReset() {
        return needsPasswordReset;
    }

    /**
     * Sets the name of the user.
     * 
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the role of the user.
     * 
     * @param role The role of the user.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Sets the gender of the user.
     * 
     * @param gender The gender of the user.
     */
    public void setGender(Staff.Gender gender) {
        this.gender = gender;
    }

    /**
     * Sets the age of the user.
     * 
     * @param age The age of the user.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the branch where the user belongs.
     * 
     * @param branch The branch where the user belongs.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Sets the salt used for hashing the password.
     * 
     * @param salt The salt used for hashing the password.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Sets the hashed password of the user.
     * 
     * @param hashedPassword The hashed password of the user.
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Sets whether the user needs to reset their password.
     * 
     * @param needsPasswordReset Indicates whether the user needs to reset their password.
     */
    public void setNeedsPasswordReset(boolean needsPasswordReset) {
        this.needsPasswordReset = needsPasswordReset;
    }

}
