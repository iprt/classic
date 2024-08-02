package dp02_factory.abstractFactory.components.detail;

import dp02_factory.abstractFactory.components.Motherboard;

/**
 * @author tech@intellij.io
 */
public class B365Motherboard implements Motherboard {
    @Override
    public String modelName() {
        return "b365";
    }
}
