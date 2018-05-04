package remote.to.gpio.services.relay;

import remote.to.gpio.models.relay.Relay;

import java.util.List;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
public interface RelaysService {
    List<Relay> getRelays();
    void setRelayName(int[] ides, String[] customNames);
    void switchRelay(int id, boolean value);
}
