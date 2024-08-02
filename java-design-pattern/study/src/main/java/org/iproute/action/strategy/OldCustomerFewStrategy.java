package org.iproute.action.strategy;


/**
 * @author tech@intellij.io
 */
public class OldCustomerFewStrategy implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("打八五折");
        return standardPrice * 0.85;
    }

}
