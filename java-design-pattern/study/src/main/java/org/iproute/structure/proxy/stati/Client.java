package org.iproute.structure.proxy.stati;

/**
 * @author tech@intellij.io
 */
public class Client {
    public static void main(String[] args) {
        Real real = new Real();
        Proxy proxy = new Proxy(real);
        proxy.func1();
        proxy.func2();
        proxy.action();
        proxy.func3();
    }
}
