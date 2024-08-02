package dp02_factory.abstractFactory2;

import dp02_factory.abstractFactory2.impl.shape.Circle;
import dp02_factory.abstractFactory2.impl.shape.Rectangle;
import dp02_factory.abstractFactory2.impl.shape.Square;

/**
 * ShapeFactory
 *
 * @author tech@intellij.io
 * @since 2021/7/9
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {

        if (shapeType == null) {
            return null;
        }

        if ("CIRCLE".equalsIgnoreCase(shapeType)) {
            return new Circle();
        } else if ("RECTANGLE".equalsIgnoreCase(shapeType)) {
            return new Rectangle();
        } else if ("SQUARE".equalsIgnoreCase(shapeType)) {
            return new Square();
        }
        return null;

    }
}
