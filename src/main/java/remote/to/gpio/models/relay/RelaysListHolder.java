package remote.to.gpio.models.relay;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import remote.to.gpio.operations.Initializer;
import remote.to.gpio.operations.Operator;
import remote.to.gpio.tools.PropertyHandler;

import java.util.*;

import static remote.to.gpio.values.Constants.*;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public class RelaysListHolder {

    private static final Logger logger = LoggerFactory.getLogger(RelaysListHolder.class);
    private static RelaysListHolder holder;

    private List<Relay> relaysList;

    private RelaysListHolder(){
        super();
        final Operator operator = Initializer.initOperator();
        relaysList = new ArrayList<>();
        GpioPinDigitalOutput[] pins = operator.getPins();
        Arrays.sort(pins, new Comparator<GpioPinDigitalOutput>() {
            @Override
            public int compare(GpioPinDigitalOutput pin1, GpioPinDigitalOutput pin2) {
                String line = SPACE;

                if (pin1.getName().split(line).length < 2 || pin2.getName().split(line).length < 2) {
                    line = UNDERLINE;
                    if (pin1.getName().split(line).length < 2 || pin2.getName().split(line).length < 2) {
                        logger.error(LOG_MARKER);
                        return 0;
                    }
                }

                int number1 = 0;
                int number2 = 0;

                try {
                    number1 = Integer.valueOf(pin1.getName().split(line)[1]);
                    number2 = Integer.valueOf(pin2.getName().split(line)[1]);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage());
                }

                return Integer.compare(number1, number2);
            }
        });

        Properties namesProperties = PropertyHandler.read(RELAY_NAMES);
        Properties timesProperties = PropertyHandler.read(RELAY_TIMES);
        String customName;
        String time;
        long timeToGo;

        for (int i = 0; i < pins.length; i++) {
            customName = namesProperties.getProperty(String.valueOf(i));
            time = timesProperties.getProperty(String.valueOf(i));
            try {
                timeToGo = Long.parseLong(time);
            } catch (Exception e) {
                logger.error(LOG_MARKER, e.getMessage());
                timeToGo = 0;
            }
            Relay relay = new Relay(pins[i].getName(), customName, pins[i].isHigh(), pins[i]);
            relay.setTime(timeToGo);
            if (relay.getTime() > new Date().getTime() / 1000) {
                new Thread(() -> {
                    while (relay.getTime() > new Date().getTime() / 1000) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            logger.error(LOG_MARKER, e.getMessage());
                        }
                    }
                    relay.toggle(false);
                }).start();
            } else {
                relay.toggle(false);
            }
            relaysList.add(relay);
        }
    }

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

    public int count() {
        return relaysList.size();
    }

    public Relay getRelay(int i) {
        if (i < 0 || i >= relaysList.size()) {
            logger.error(LOG_MARKER + "\tRelay not found!");
            return null;
        }
        return relaysList.get(i);
    }
}
