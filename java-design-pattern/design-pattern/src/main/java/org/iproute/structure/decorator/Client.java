package org.iproute.structure.decorator;

/**
 * @author winterfell
 */
public class Client {
    public static void main(String[] args) {
        ICar car = new Car();
        ICar supperCar = new FlyCar(car);
        supperCar.move();
    }
}
