package remote.to.gpio.operations.orange;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
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
    @Override
    public GpioController getGpioController() {
        try {
            PlatformManager.setPlatform(Platform.ORANGEPI);
        } catch (PlatformAlreadyAssignedException e) {
            logger.error(Constants.LOG_MARKER + e.getMessage());
        }
        return GpioFactory.getInstance();
    }
}
