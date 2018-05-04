package remote.to.gpio.services.mode;

import remote.to.gpio.tools.PropertyHandler;

import java.util.Properties;

import static remote.to.gpio.values.Constants.CURRENT_MODE;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
public class ModeServiceImpl implements ModeService {
    @Override
    public String getMode() {
        Properties properties = PropertyHandler.read(CURRENT_MODE);
        return properties.getProperty("mode");
    }

    @Override
    public void setMode(String mode) {
        Properties properties = PropertyHandler.read(CURRENT_MODE);
        properties.setProperty("mode", mode);
        PropertyHandler.write(properties, CURRENT_MODE);
    }
}
