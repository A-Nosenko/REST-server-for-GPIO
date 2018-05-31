package remote.to.gpio.models.relay;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Model of one relay.
 *
 * @author Anatolii Nosenko
 * @version 1.0 4/20/2018.
 */
public class Relay {

    private final String technicalName;
    private String customName;
    private boolean enabled;
    private GpioPinDigitalOutput pin;

    public Relay(String technicalName, String customName, boolean enabled, GpioPinDigitalOutput pin) {
        this.technicalName = technicalName;
        this.customName = customName;
        this.enabled = enabled;
        this.pin = pin;
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean toggle(boolean status) {

        if (status) {
            pin.high();
        } else {
            pin.low();
        }
        this.setEnabled(pin.isHigh());
        return status;
    }
}
