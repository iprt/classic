package org.iproute.creating.factory.simplefactory;

/**
 * @author tech@intellij.io
 */
public class CarFactory2 {
    public static Car createByd() {
        return new Byd();
    }

    public static Car createAudi() {
        return new Audi();
    }
}
