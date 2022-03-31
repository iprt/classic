package org.iproute.other.filter;

/**
 * @author winterfell
 */
public class InterceptorFilterDemo {

    public static void main(String[] args) {
        Target target = new Target();

        FilterManager filterManager = new FilterManager(target);
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client(filterManager);

        client.sendRequest("HOME");
    }

}
