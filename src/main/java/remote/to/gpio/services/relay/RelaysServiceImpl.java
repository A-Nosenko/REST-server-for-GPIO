package remote.to.gpio.services.relay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import remote.to.gpio.models.relay.Relay;
import remote.to.gpio.models.relay.RelayReport;
import remote.to.gpio.models.relay.RelaysListHolder;
import remote.to.gpio.tools.PropertyHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static remote.to.gpio.values.Constants.LOG_MARKER;
import static remote.to.gpio.values.Constants.RELAY_NAMES;
import static remote.to.gpio.values.Constants.RELAY_TIMES;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
public class RelaysServiceImpl implements RelaysService {

    private static final Logger logger = LoggerFactory.getLogger(RelaysServiceImpl.class);
    private RelaysListHolder relaysListHolder = RelaysListHolder.getHolder();

    @Override
    public List<RelayReport> getRelayReports() {
        List<Relay> relays = relaysListHolder.getRelaysList();
        List<RelayReport> relayReports = new ArrayList<>();
        if (relays == null) {
            return relayReports;
        }
        int i = 0;
        for (Relay relay : relays) {
            relayReports.add(new RelayReport(i++, relay));
        }
        return relayReports;
    }

    @Override
    public RelayReport getRelayReport(int i) {
        Relay relay = relaysListHolder.getRelay(i);
        return new RelayReport(i, relay);
    }

    @Override
    public int setRelayNames(int[] ides, String[] customNames) {
        Properties properties = PropertyHandler.read(RELAY_NAMES);
        if (ides.length != customNames.length) {
            logger.error(LOG_MARKER + ides.length + " != " + customNames.length);
        }
        for (int i = 0; i < ides.length; i++) {
            if (!validateRelayName(customNames[i])) {
                continue;
            }
            properties.setProperty(String.valueOf(ides[i]), customNames[i]);
            relaysListHolder.getRelay(i).setCustomName(customNames[i]);
        }
        PropertyHandler.write(properties, RELAY_NAMES);
        return 1;
    }

    @Override
    public int setRelayName(int id, String customName) {
        if (!validateRelayName(customName)) {
            return -1;
        }
        Properties properties = PropertyHandler.read(RELAY_NAMES);

        properties.setProperty(String.valueOf(id), customName);
        relaysListHolder.getRelay(id).setCustomName(customName);

        PropertyHandler.write(properties, RELAY_NAMES);
        return 1;
    }

    @Override
    public int switchRelayOn(int id) {
        logger.info(LOG_MARKER + "SwitchRelayOn(" + id + ")");
        return relaysListHolder.getRelay(id).toggle(true);
    }

    @Override
    public int switchRelayOn(int id, int timeToGo) {
        logger.info(LOG_MARKER + "SwitchRelayOn(" + id + ")");
        installTimer(id, timeToGo);
        return relaysListHolder.getRelay(id).toggle(true);
    }

    private void installTimer(int id, int timeToGo) {
        Relay relay = relaysListHolder.getRelay(id);
        if (relay == null || timeToGo <= 0) {
            return;
        }
        long time = new Date().getTime() / 1000 + timeToGo;
        relay.setTime(time);
        saveTime(id, time);
        new Thread(() -> {
            while (relay.getTime() > new Date().getTime() / 1000 && relay.isEnabled()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error(LOG_MARKER, e.getMessage());
                }
            }
            switchRelayOff(id);
        }).start();
    }

    @Override
    public void addTime(int id, int time) {
        Relay relay = relaysListHolder.getRelay(id);
        if (relay.getTime() == 0) {
            installTimer(id, time);
            return;
        }

        long timeToGo = relay.getTime() + time;
        relay.setTime(timeToGo);
        saveTime(id, timeToGo);
    }

    @Override
    public int switchRelayOff(int id) {
        Relay relay = relaysListHolder.getRelay(id);
        relay.setTime(0);
        return relay.toggle(false);
    }

    @Override
    public int count() {
        return relaysListHolder.count();
    }

    private boolean validateRelayName(String name) {
        if (name == null || name.isEmpty() || name.length() > 500) {
            return false;
        }
        return true;
    }

    private void saveTime(int id, long time) {
        Properties properties = PropertyHandler.read(RELAY_TIMES);
        properties.setProperty(String.valueOf(id), String.valueOf(time));
        PropertyHandler.write(properties, RELAY_TIMES);
    }

    @Override
    public void switchAllRelaysOn() {
        relaysListHolder.getRelaysList().forEach(relay -> relay.toggle(true));
    }

    @Override
    public void switchAllRelaysOff() {
        relaysListHolder.getRelaysList().forEach(relay -> relay.toggle(false));
    }
}
