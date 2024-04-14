public class UserCredentials {
    String name;
    String role;
    String gender;
    int age;
    String branch;
    String salt;
    String hashedPassword;
    boolean needsPasswordReset;

    public UserCredentials(String name, String role, String gender, int age, String branch,
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
}
