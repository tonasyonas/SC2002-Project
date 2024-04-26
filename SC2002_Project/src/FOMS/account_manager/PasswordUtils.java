package FOMS.account_manager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * The PasswordUtils class provides utility methods for generating and hashing passwords.
 * It also includes a method for verifying passwords.
 * 
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public class PasswordUtils {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALGORITHM = "SHA-256";

    /**
     * Generates a random salt.
     * @return A randomly generated salt encoded in Base64.
     */
    public static String getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hashes the given password using the specified salt.
     * @param password The password to hash.
     * @param salt The salt used for hashing.
     * @return The hashed password encoded in Base64.
     */
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error during hashing", e);
        }
    }

    /**
     * Verifies whether the given password matches the provided hash and salt.
     * @param password The password to verify.
     * @param salt The salt used for hashing.
     * @param hash The hashed password to compare against.
     * @return true if the password matches the hash, false otherwise.
     */
    public static boolean verifyPassword(String password, String salt, String hash) {
        String newHash = hashPassword(password, salt);
        return newHash.equals(hash);
    }
}
