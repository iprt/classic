package dp02_factory.abstractFactory2;

import dp02_factory.abstractFactory2.impl.color.Blue;
import dp02_factory.abstractFactory2.impl.color.Green;
import dp02_factory.abstractFactory2.impl.color.Red;

/**
 * ColorFactory
 *
 * @author tech@intellij.io
 * @since 2021/7/9
 */
public class ColorFactory extends AbstractFactory {


    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }

        if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        } else if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        }

        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }
}
