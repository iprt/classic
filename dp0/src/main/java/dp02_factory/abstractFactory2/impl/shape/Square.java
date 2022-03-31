package dp02_factory.abstractFactory2.impl.shape;

import dp02_factory.abstractFactory2.Shape;

/**
 * Square
 *
 * @author winterfell
 * @since 2021/7/9
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
