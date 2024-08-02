package org.iproute.other.filter;

/**
 * @author tech@intellij.io
 */
public class Client {

    private FilterManager filterManager;

    public Client(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    public void sendRequest(String request) {
        this.filterManager.filterRequest(request);
    }
}
