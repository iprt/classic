package dp02_factory.abstractFactory2.impl.shape;

import dp02_factory.abstractFactory2.Shape;

/**
 * Circle
 *
 * @author winterfell
 * @since 2021/7/9
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
