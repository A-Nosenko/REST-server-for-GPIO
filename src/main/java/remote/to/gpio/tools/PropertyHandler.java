package remote.to.gpio.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import remote.to.gpio.services.user.SecurityService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/18/2018.
 */
public class PropertyHandler {

    private static final Logger logger = LoggerFactory.getLogger(PropertyHandler.class);

    public static Properties read(String path) {
        Properties properties = new Properties();
        try(FileInputStream in = new FileInputStream(path)) {
            properties.load(in);
        } catch (IOException e) {
            logger.error(SecurityService.class.getCanonicalName(), e.getMessage());
        }
        return properties;
    }

    public static void write(Properties properties, String path){
        try(FileOutputStream out = new FileOutputStream(path)) {
            properties.store(out, null);
        } catch (IOException e) {
            logger.error(SecurityService.class.getCanonicalName(), e.getMessage());
        }
    }
}
