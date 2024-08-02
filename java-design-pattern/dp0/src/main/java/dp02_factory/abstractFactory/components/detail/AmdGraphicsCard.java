package dp02_factory.abstractFactory.components.detail;

import dp02_factory.abstractFactory.components.GraphicsCard;

/**
 * @author tech@intellij.io
 */
public class AmdGraphicsCard implements GraphicsCard {
    @Override
    public String graphicsCardName() {
        return "amd";
    }
}
