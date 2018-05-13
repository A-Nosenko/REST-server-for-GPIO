package remote.to.gpio.services.relay;

import remote.to.gpio.models.relay.RelayReport;

import java.util.List;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
public interface RelaysService {
    List<RelayReport> getRelays();
    RelayReport getRelay(int i);
    void setRelayName(int[] ides, String[] customNames);
    void switchRelay(int id, boolean value);
    int count();
}
