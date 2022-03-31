package dp02_factory.factorymethod;

/**
 * @author winterfell
 */
public class ServerSocketChannelFactory implements ChannelFactory {
    @Override
    public Channel getChannel() {
        return new ServerSocketChannel();
    }
}
