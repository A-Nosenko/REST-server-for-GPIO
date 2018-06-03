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
    int setRelayNames(int[] ides, String[] customNames);
    int setRelayName(int id, String customName);
    int switchRelay(int id, boolean value);
    int count();
}
