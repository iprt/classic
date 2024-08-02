package org.iproute.creating.factory.factorymethod;

/**
 * @author tech@intellij.io
 */
public class BydCarFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Byd();
    }

}
