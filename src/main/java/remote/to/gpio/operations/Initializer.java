package remote.to.gpio.operations;

import remote.to.gpio.operations.orange.OperatorOrangeImpl;

/**
 * Specifies platform.
 *
 * @author Anatolii Nosenko
 * @version 1.0 4/22/2018.
 */
public class Initializer {
    public static Operator initOperator() {
        return new OperatorOrangeImpl();
    }
}
