package remote.to.gpio.values;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/18/2018.
 */
public class Constants {
    public static final String CURRENT_MODE =  System.getProperty("user.dir") +
            "/../webapps/ROOT/WEB-INF/classes/current_mode.properties";
    public static final String RELAY_NAMES =  System.getProperty("user.dir") +
            "/../webapps/ROOT/WEB-INF/classes/relay_names.properties";
    public static final String RELAY_STATES =  System.getProperty("user.dir") +
            "/../webapps/ROOT/WEB-INF/classes/relay_states.properties";
    public static final String SECURITY = System.getProperty("user.dir") +
            "/../webapps/ROOT/WEB-INF/classes/security.properties";
    public static final String LOG_MARKER = "\n\n\n\n" +
            "####################################################################" +
            "\n\n\n\n";
    public static final String SPACE = " ";
    public static final String UNDERLINE = "_";
}
