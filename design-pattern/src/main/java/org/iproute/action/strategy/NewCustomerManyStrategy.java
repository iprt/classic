package org.iproute.action.strategy;

/**
 * @author winterfell
 */
public class NewCustomerManyStrategy implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("打九折");
        return standardPrice * 0.9;
    }

}
