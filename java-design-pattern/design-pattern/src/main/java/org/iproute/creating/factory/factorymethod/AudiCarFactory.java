package org.iproute.creating.factory.factorymethod;

/**
 * @author tech@intellij.io
 */
public class AudiCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Audi();
    }
}
