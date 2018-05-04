package remote.to.gpio.operations;

import com.pi4j.io.gpio.GpioController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides GpioController in specified platform.
 *
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public interface Operator {
    Logger logger = LoggerFactory.getLogger(Operator.class);
    GpioController getGpioController();
}
