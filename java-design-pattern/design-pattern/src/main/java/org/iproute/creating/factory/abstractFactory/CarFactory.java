package org.iproute.creating.factory.abstractFactory;

/**
 * @author tech@intellij.io
 */
public interface CarFactory {
    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}

