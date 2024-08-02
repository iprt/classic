package dp02_factory.factorymethod;

/**
 * @author tech@intellij.io
 */
public class ServerSocketChannel implements Channel {
    @Override
    public String name() {
        return "ServerSocketChannel";
    }
}
