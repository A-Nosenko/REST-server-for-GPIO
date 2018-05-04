package remote.to.gpio.services.relay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import remote.to.gpio.models.relay.Relay;
import remote.to.gpio.models.relay.RelaysListHolder;
import remote.to.gpio.tools.PropertyHandler;

import java.util.List;
import java.util.Properties;

import static remote.to.gpio.values.Constants.*;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
public class RelaysServiceImpl implements RelaysService {

    private static final Logger logger = LoggerFactory.getLogger(RelaysServiceImpl.class);
    private RelaysListHolder relaysListHolder = RelaysListHolder.getHolder();

    @Override
    public List<Relay> getRelays(){
        return relaysListHolder.getRelaysList();
    }

    @Override
    public void setRelayName(int[] ides, String[] customNames) {
        Properties properties = PropertyHandler.read(RELAY_NAMES);
        if (ides.length != customNames.length) {
            logger.error(LOG_MARKER + ides.length + " != " + customNames.length);
        }
        for ( int i = 0; i < ides.length; i++) {
            properties.setProperty(String.valueOf(ides[i]), customNames[i]);
            relaysListHolder.setRelayName(i, customNames[i]);
        }
        PropertyHandler.write(properties, RELAY_NAMES);
    }

    @Override
    public void switchRelay(int id, boolean status) {
        Properties properties = PropertyHandler.read(RELAY_STATES);
        properties.setProperty(String.valueOf(id),
                relaysListHolder.switchRelay(id, status) ? "On" : "Off");
        PropertyHandler.write(properties, RELAY_STATES);
    }
}
