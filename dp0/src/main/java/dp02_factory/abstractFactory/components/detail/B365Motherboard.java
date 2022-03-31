package dp02_factory.abstractFactory.components.detail;

import dp02_factory.abstractFactory.components.Motherboard;

/**
 * @author winterfell
 */
public class B365Motherboard implements Motherboard {
    @Override
    public String modelName() {
        return "b365";
    }
}
