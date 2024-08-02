package dp02_factory.abstractFactory2;

/**
 * Main
 *
 * @author tech@intellij.io
 * @since 2021/7/9
 */
public class Main {

    public static void main(String[] args) {

        // 获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");

        assert shapeFactory != null;

        Shape circle = shapeFactory.getShape("circle");
        circle.draw();

        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();

        Shape square = shapeFactory.getShape("square");
        square.draw();


        // 获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("color");

        assert colorFactory != null;

        Color red = colorFactory.getColor("red");
        red.fill();

        Color blue = colorFactory.getColor("blue");
        blue.fill();

        Color green = colorFactory.getColor("green");
        green.fill();

    }
}
