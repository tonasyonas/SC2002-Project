

public interface ILogin {
    /**
     * Logs in a user with the specified user ID and password.
     *
     * @param userID   the ID of the user
     * @param password the user's password
     * @return true if the login was successful, false otherwise
     */
    public boolean login(String us, String password);

    /**
     * Logs out the currently logged in user.
     *
     * @return true if the logout was successful, false otherwise
     */
    public boolean logout();
}