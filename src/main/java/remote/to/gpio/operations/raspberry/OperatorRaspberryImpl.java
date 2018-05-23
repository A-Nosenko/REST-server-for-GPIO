package remote.to.gpio.operations.raspberry;

import com.pi4j.io.gpio.*;
import remote.to.gpio.operations.Operator;

/**
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public class OperatorRaspberryImpl implements Operator {
    @Override
    public GpioController getGpioController() {
        return GpioFactory.getInstance();
    }

    @Override
    public GpioPinDigitalOutput[] getPins() {
        GpioController gpioController = getGpioController();
        Pin[] outPins = RaspiPin.allPins(PinMode.DIGITAL_OUTPUT);
        int count = outPins.length;
        if (count < 1) {
            return null;
        }
        GpioPinDigitalOutput[] pins = new GpioPinDigitalOutput[outPins.length];
        for (int i = 0; i <= count; i++) {
            pins[i] = gpioController.provisionDigitalOutputPin(outPins[i]);
        }
        return pins;
    }
}
