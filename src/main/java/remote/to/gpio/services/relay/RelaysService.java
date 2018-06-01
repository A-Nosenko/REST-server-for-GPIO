package remote.to.gpio.services.relay;

import remote.to.gpio.models.relay.RelayReport;

import java.util.List;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/21/2018.
 */
public interface RelaysService {
    List<RelayReport> getRelayReports();
    RelayReport getRelayReport(int i);
    int setRelayName(int[] ides, String[] customNames);
    int switchRelay(int id, boolean value);
    int count();
}
