

import java.util.Map;



/**
 * The {@link AuthStudentService} class extends {@link AuthService} and
 * provides the login functionality for students.
 */
public class LoginStaff extends Login {
    /**
     * Constructs an instance of the {@link AuthStudentService} class.
     */
    public LoginStaff() {
    };

    @Override
    public boolean login(int userID, String password) {
       
        int staff = staff.getStaffID();

        if (authenticate(student, password) == false)
            return false;

        AuthStore.setCurrentUser(student);
        return true;
    }

}