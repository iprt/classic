package org.iproute.creating.factory.abstractFactory;

/**
 * @author winterfell
 */
/**
 * @author winterfell
 */
public class Client {
    public static void main(String[] args) {
        CarFactory factory = new LuxuryCarFactory();
        Engine e = factory.createEngine();
        e.run();
        e.start();
    }
}
