package remote.to.gpio.models.relay;

/**
 * Class to send information about relay to client.
 *
 * @author Anatolii Nosenko
 * @version 1.0 5/9/2018.
 */
public class RelayReport {
    private final String technicalName;
    private final String customName;
    private final boolean enabled;

    public RelayReport(Relay relay) {
        this.technicalName = relay.getTechnicalName();
        this.customName = relay.getCustomName();
        this.enabled = relay.isEnabled();
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public String getCustomName() {
        return customName;
    }

    public boolean isEnabled() {
        return enabled;
    }
}