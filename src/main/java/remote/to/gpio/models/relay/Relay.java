package remote.to.gpio.models.relay;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static remote.to.gpio.values.Constants.*;

/**
 * Model of one relay.
 *
 * @author Anatolii Nosenko
 * @version 1.0 4/20/2018.
 */
public class Relay implements Comparable<Relay> {

    private static final Logger logger = LoggerFactory.getLogger(Relay.class);

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

    @Override
    public int compareTo(Relay relay) {
        String line = SPACE;

        if (this.technicalName.split(line).length < 2 || relay.getTechnicalName().split(line).length < 2) {
            line = UNDERLINE;
            if (this.technicalName.split(line).length < 2 || relay.getTechnicalName().split(line).length < 2) {
                logger.error(LOG_MARKER);
                return 0;
            }
        }

        int number1 = 0;
        int number2 = 0;

        try {
            number1 = Integer.valueOf(this.technicalName.split(line)[1]);
            number2 = Integer.valueOf(relay.getTechnicalName().split(line)[1]);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        return Integer.compare(number1, number2);
    }
}
