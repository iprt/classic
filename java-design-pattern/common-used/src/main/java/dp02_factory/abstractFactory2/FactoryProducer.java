package dp02_factory.abstractFactory2;

/**
 * FactoryProducer
 *
 * @author tech@intellij.io
 * @since 2021/7/9
 */
public class FactoryProducer {

    /**
     * Gets factory.
     *
     * @param choice the choice
     * @return the factory
     */
    public static AbstractFactory getFactory(String choice) {

        if ("SHAPE".equalsIgnoreCase(choice)) {
            return new ShapeFactory();
        } else if ("COLOR".equalsIgnoreCase(choice)) {
            return new ColorFactory();
        }
        return null;
    }
}
