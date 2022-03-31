package org.iproute.other.filter;

/**
 * @author winterfell
 */
public class Target {

    public void execute(String request) {
        System.out.printf("Executing request : " + request);
    }
}
