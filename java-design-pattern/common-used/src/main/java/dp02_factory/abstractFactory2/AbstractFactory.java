package dp02_factory.abstractFactory2;

/**
 * AbstractFactory
 *
 * @author tech@intellij.io
 * @since 2021/7/9
 */
public abstract class AbstractFactory {

    /**
     * Gets color.
     *
     * @param color the color
     * @return the color
     */
    public abstract Color getColor(String color);

    /**
     * Gets shape.
     *
     * @param shapeType the shape type
     * @return the shape
     */
    public abstract Shape getShape(String shapeType);
}
