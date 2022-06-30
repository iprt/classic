package org.iproute.creating.factory.factorymethod;

/**
 * @author winterfell
 */
public class BydCarFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Byd();
    }

}
