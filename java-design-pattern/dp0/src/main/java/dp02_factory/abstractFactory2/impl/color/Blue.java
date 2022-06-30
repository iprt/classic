package dp02_factory.abstractFactory2.impl.color;

import dp02_factory.abstractFactory2.Color;

/**
 * Blue
 *
 * @author winterfell
 * @since 2021/7/9
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
