package remote.to.gpio.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/19/2018.
 */
public class PasswordParser {

    private static final Logger logger = LoggerFactory.getLogger(PasswordParser.class);

    /**
     * The workload on password hashes generator.
     * May be from 10 to 31.
     */
    private static final int WORKLOAD = 12;

    /**
     * Represents password in to the hash line.
     *
     * @param password Key value provided from user.
     * @return Line of length 60 that is the bcrypt hashed password in crypt(3) format.
     */
    public static String hidePassword(String password) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(password, salt);
    }

    /**
     * @param password Key value to check.
     * @param hash Value that stored in database.
     * @return True in case conformity password by the cache.
     */
    public static boolean checkPassword(String password, String hash) {
        if (hash == null || !hash.startsWith("$2a$")) {
            logger.error("Missed password hash!");
            return false;
        }
        return BCrypt.checkpw(password, hash);
    }
}
