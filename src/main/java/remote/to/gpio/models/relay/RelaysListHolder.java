package remote.to.gpio.models.relay;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import remote.to.gpio.operations.Initializer;
import remote.to.gpio.operations.Operator;
import remote.to.gpio.tools.PropertyHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static remote.to.gpio.values.Constants.*;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public class RelaysListHolder {

    private static final Logger logger = LoggerFactory.getLogger(RelaysListHolder.class);
    private static RelaysListHolder holder;

    private RelaysListHolder(){
        super();
        final Operator operator = Initializer.initOperator();
        List<Relay> relays = new ArrayList<>();

        GpioPinDigitalOutput[] pins = operator.getPins();

        Properties namesProperties = PropertyHandler.read(RELAY_NAMES);
        Properties statesProperties = PropertyHandler.read(RELAY_STATES);
        String customName;
        boolean state;

        for (int i = 0; i < pins.length; i++) {
            customName = namesProperties.getProperty(String.valueOf(i));
            state = "On".equals(statesProperties.getProperty(String.valueOf(i)));
            if (state) {
                pins[i].high();
            } else {
                pins[i].low();
            }
            if ((state && !pins[i].isHigh()) || (state && !pins[i].isLow())) {
                logger.error(LOG_MARKER + "Relay toggle error! \n" + pins[i]);
                throw new RuntimeException();
            }
            relays.add(new Relay(pins[i].getName(), customName, state, pins[i]));
        }
        relays.sort(Relay::compareTo);

        relaysList =  relays;
    }

    private List<Relay> relaysList;

    public static RelaysListHolder getHolder() {
        if (holder == null) {
            holder = new RelaysListHolder();
            return holder;
        }
        return holder;
    }

    public List<Relay> getRelaysList() {
        return relaysList;
    }

    public void setRelayName(int id, String name) {
        if (id < relaysList.size()) {
            return;
        }
        Relay relay = relaysList.get(id);
        if (relay == null) {
            logger.error(LOG_MARKER + "\tRelay not found!");
            return;
        }
        relay.setCustomName(name);
    }

    public boolean switchRelay(int id, boolean status) {
        if (id < relaysList.size()) {
            return false;
        }
        Relay relay = relaysList.get(id);
        if (relay == null) {
            logger.error(LOG_MARKER + "\tRelay not found!");
            return false;
        }
        return relay.toggle(status);
    }

    public int count() {
        return relaysList.size();
    }

    public Relay getRelay(int i) {
        if (i < relaysList.size()) {
            logger.error(LOG_MARKER + "\tRelay not found!");
            return null;
        }
        return relaysList.get(i);
    }
}
