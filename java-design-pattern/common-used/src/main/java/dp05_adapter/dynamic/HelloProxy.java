package dp05_adapter.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * HelloProxy
 *
 * @author tech@intellij.io
 * @since 2021/7/13
 */
public class HelloProxy implements InvocationHandler {

    private final Object subject;

    public HelloProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        Object invoke = method.invoke(subject, args);
        System.out.println("After invoke " + method.getName());
        return invoke;
    }
}
