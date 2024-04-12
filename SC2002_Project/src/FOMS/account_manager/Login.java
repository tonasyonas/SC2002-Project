


import FOMS.*;


/**
 * The {@link AuthService} class is an abstract class that implements the
 * {@link IAuthService} interface.
 * It provides basic authentication functionalities for user login and logout.
 */
public abstract class Login implements ILogin {
    public abstract boolean login(int staffID, String password);

    /**
     * Constructs an instance of the {@link AuthService} class.
     */
    public Login() {
    };

    @Override
    public boolean logout() {
        return false;
    };

    /**
     * Authenticates the given user with the given password.
     * 
     * @param user     the user to be authenticated
     * @param password the password to be used for authentication
     * @return true if the user is authenticated successfully, false otherwise
     */
    protected boolean authenticate(Staff staffID, String password) {
        if (staffID == null)
            return false;
        if (!staffID.getstaffPassword().equals(password))
            return false;
        return true;
    }
}