package remote.to.gpio.values;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/18/2018.
 */
public class Constants {
    static {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            CURRENT_MODE =  System.getProperty("user.dir") +
                    "/../webapps/ROOT/WEB-INF/classes/current_mode.properties";
            RELAY_NAMES =  System.getProperty("user.dir") +
                    "/../webapps/ROOT/WEB-INF/classes/relay_names.properties";
            RELAY_STATES =  System.getProperty("user.dir") +
                    "/../webapps/ROOT/WEB-INF/classes/relay_states.properties";
            SECURITY = System.getProperty("user.dir") +
                    "/../webapps/ROOT/WEB-INF/classes/security.properties";

        } else if (OS.contains("linux")) {
            CURRENT_MODE = "/opt/tomcat/webapps/ROOT/WEB-INF/classes/current_mode.properties";
            RELAY_NAMES = "/opt/tomcat/webapps/ROOT/WEB-INF/classes/relay_names.properties";
            RELAY_STATES = "/opt/tomcat/webapps/ROOT/WEB-INF/classes/relay_states.properties";
            SECURITY = "/opt/tomcat/webapps/ROOT/WEB-INF/classes/security.properties";
        } else {
            CURRENT_MODE = null;
            RELAY_NAMES = null;
            RELAY_STATES = null;
            SECURITY = null;
        }
    }

    public static final String CURRENT_MODE;
    public static final String RELAY_NAMES;
    public static final String RELAY_STATES;
    public static final String SECURITY;


    public static final String LOG_MARKER = "\n\n\n\n" +
            "####################################################################" +
            "\n\n\n\n";
    public static final String SPACE = " ";
    public static final String UNDERLINE = "_";
}
