package org.iproute.structure.proxy.dynamic;

/**
 * @author tech@intellij.io
 */
public class Client {
    public static void main(String[] args) {
        Action real = new Real();
        MyInvocationHandler<Action> h = new MyInvocationHandler<>(real);
        Action proxy = h.getProxy();
        proxy.action();

    }
}
