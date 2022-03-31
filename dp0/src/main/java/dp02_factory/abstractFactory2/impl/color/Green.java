package dp02_factory.abstractFactory2.impl.color;

import dp02_factory.abstractFactory2.Color;

/**
 * Green
 *
 * @author winterfell
 * @since 2021/7/9
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
