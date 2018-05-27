package remote.to.gpio.operations.orange;

import com.pi4j.io.gpio.*;
import com.pi4j.platform.Platform;
import com.pi4j.platform.PlatformAlreadyAssignedException;
import com.pi4j.platform.PlatformManager;
import remote.to.gpio.operations.Operator;
import remote.to.gpio.values.Constants;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public class OperatorOrangeImpl implements Operator {

    static {
        try {
            PlatformManager.setPlatform(Platform.ORANGEPI);
        } catch (PlatformAlreadyAssignedException e) {
            logger.error(Constants.LOG_MARKER + e.getMessage());
        }
    }

    @Override
    public GpioController getGpioController() {
        return GpioFactory.getInstance();
    }

    @Override
    public GpioPinDigitalOutput[] getPins() {
        GpioController gpioController = getGpioController();
        Pin[] outPins = OrangePiPin.allPins(PinMode.DIGITAL_OUTPUT);
        int count = outPins.length;
        GpioPinDigitalOutput[] pins = new GpioPinDigitalOutput[outPins.length];

        for (int i = 0; i < count; i++) {
            pins[i] = gpioController.provisionDigitalOutputPin(outPins[i]);
        }
        return pins;
    }
}
