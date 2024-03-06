package utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class to hash a password.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class HashPassword {
	/**
	 * @param password The user input for password that is to be hashed.
	 * @return The hexed password.
	 */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string representation.
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
