package remote.to.gpio.operations;

import com.pi4j.platform.Platform;
import com.pi4j.platform.PlatformManager;
import remote.to.gpio.operations.orange.OperatorOrangeImpl;
import remote.to.gpio.operations.raspberry.OperatorRaspberryImpl;

/**
 * Specifies platform.
 *
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public class Initializer {
    public static Operator initOperator() {
        Platform platform = PlatformManager.getPlatform();
        switch (platform) {
            case RASPBERRYPI:
                return new OperatorRaspberryImpl();
            case ORANGEPI:
                return new OperatorOrangeImpl();
            default:
                return null;
        }
    }
}
