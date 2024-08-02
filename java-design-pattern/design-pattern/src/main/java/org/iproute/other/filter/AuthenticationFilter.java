package org.iproute.other.filter;

/**
 * @author tech@intellij.io
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}
