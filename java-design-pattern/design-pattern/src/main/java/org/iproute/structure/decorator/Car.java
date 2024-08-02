package org.iproute.structure.decorator;

/**
 * @author tech@intellij.io
 */
public class Car implements ICar {

    @Override
    public void move() {
        System.out.println("车子普通的跑");
    }
}
