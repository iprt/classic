package dp05_adapter.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Main
 *
 * @author winterfell
 * @since 2021/7/13
 */
public class Main {

    public static void main(String[] args) {
        Hello hello = new Hello();

        InvocationHandler handler = new HelloProxy(hello);

        HelloInterface helloInterface = (HelloInterface) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                handler);


        helloInterface.sayHello();

    }
}
