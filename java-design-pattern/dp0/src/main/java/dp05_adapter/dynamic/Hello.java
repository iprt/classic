package dp05_adapter.dynamic;

/**
 * Hello
 *
 * @author tech@intellij.io
 * @since 2021/7/13
 */
public class Hello implements HelloInterface {

    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
