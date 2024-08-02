package org.iproute.action.strategy;

/**
 * @author tech@intellij.io
 */
public class NewCustomerFewStrategy implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("不打折，原价");
        return standardPrice;
    }

}
