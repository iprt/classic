package dp02_factory.abstractFactory2.impl.shape;

import dp02_factory.abstractFactory2.Shape;

/**
 * step2. 创建实现接口的实体类。
 *
 * @author tech@intellij.io
 * @since 2021/7/9
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {

        System.out.println("Inside Rectangle::draw() method.");

    }
}
