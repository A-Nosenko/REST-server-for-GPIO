package remote.to.gpio.services.relay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import remote.to.gpio.models.relay.Relay;
import remote.to.gpio.models.relay.RelayReport;
import remote.to.gpio.models.relay.RelaysListHolder;
import remote.to.gpio.tools.PropertyHandler;

import java.util.ArrayList;
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
    public List<RelayReport> getRelayReports(){
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
    public RelayReport getRelay(int i){
        Relay relay = relaysListHolder.getRelay(i);
        return new RelayReport(i, relay);
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
        logger.info(LOG_MARKER + "Relays service is activated." + LOG_MARKER);
        relaysListHolder.switchRelay(id, status);
    }

    @Override
    public int count() {
        return relaysListHolder.count();
    }
}
