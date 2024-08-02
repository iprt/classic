package dp05_adapter.basic;

/**
 * @author tech@intellij.io
 */
public class Adapter implements Target {

    private final Target adaptee;

    @Override
    public String getValue() {

        System.out.println("old --> " + adaptee.getValue());

        return "这个是新的适配值";
    }

    public Adapter(Target adaptee) {
        this.adaptee = adaptee;
    }
}
