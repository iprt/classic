package dp02_factory.abstractFactory.components.detail;

import dp02_factory.abstractFactory.components.Motherboard;

/**
 * @author winterfell
 */
public class Z390Motherboard implements Motherboard {
    @Override
    public String modelName() {
        return "Z390Motherboard";
    }
}
