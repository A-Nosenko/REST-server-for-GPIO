package remote.to.gpio.models.relay;

/**
 * Class to send information about relay to client.
 *
 * @author Anatolii Nosenko
 * @version 1.0 5/9/2018.
 */
public class RelayReport {
    private final int id;
    private final String technicalName;
    private final String customName;
    private final boolean enabled;

    public RelayReport(int id, Relay relay) {
        this.id = id;
        this.technicalName = relay.getTechnicalName();
        this.customName = relay.getCustomName();
        this.enabled = relay.isEnabled();
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public int getId() {
        return id;
    }

    public String getCustomName() {
        return customName;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
