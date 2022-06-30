package dp02_factory.factorymethod;

/**
 * @author winterfell
 */
public class SocketChannelFactory implements ChannelFactory {
    @Override
    public Channel getChannel() {
        return new SocketChannel();
    }
}
