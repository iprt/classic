package dp02_factory.factorymethod;

/**
 * @author winterfell
 */
public class ServerSocketChannel implements Channel {
    @Override
    public String name() {
        return "ServerSocketChannel";
    }
}
