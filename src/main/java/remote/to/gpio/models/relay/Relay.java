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
    private long time;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int toggle(boolean status) {
        int result = 0;
        if (status) {
            pin.high();
            result = 1;
        } else {
            pin.low();
        }
        this.setEnabled(pin.isHigh());
        return result;
    }
}
