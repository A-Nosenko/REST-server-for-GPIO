package remote.to.gpio.operations.raspberry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
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
}
