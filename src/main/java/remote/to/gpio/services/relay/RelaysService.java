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
    int switchRelayOn(int id, int timeToGo);
    int switchRelayOn(int id);
    int switchRelayOff(int id);
    void addTime(int id, int time);
    int count();
    void switchAllRelaysOn();
    void switchAllRelaysOff();
}
