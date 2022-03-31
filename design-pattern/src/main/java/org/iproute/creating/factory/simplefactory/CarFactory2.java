package org.iproute.creating.factory.simplefactory;

/**
 * @author winterfell
 */
public class CarFactory2 {
    public static Car createByd() {
        return new Byd();
    }

    public static Car createAudi() {
        return new Audi();
    }
}
