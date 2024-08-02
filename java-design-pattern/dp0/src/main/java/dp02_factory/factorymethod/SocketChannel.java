package dp02_factory.factorymethod;

/**
 * @author tech@intellij.io
 */
public class SocketChannel implements Channel {
    @Override
    public String name() {
        return "SocketChannel";
    }
}
