package dine.swipe.Dine.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Hash a password (automatically includes a salt)
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // Verify password
    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
