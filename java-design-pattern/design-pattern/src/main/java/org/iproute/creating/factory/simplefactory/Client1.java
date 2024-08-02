package org.iproute.creating.factory.simplefactory;

/**
 * 测试CarFactory
 *
 * @author tech@intellij.io
 */
/**
 * @author tech@intellij.io
 */
public class Client1 {
    public static void main(String[] args) {
        Car car = CarFactory.createCar("奥迪");
        car.run();
    }
}
