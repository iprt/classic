package dp05_adapter.basic;

/**
 * @author tech@intellij.io
 */
public class Adaptee implements Target {

    @Override
    public String getValue() {
        return "原来接口的返回值";
    }

}
