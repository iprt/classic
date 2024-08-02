package dp02_factory.factorymethod;

/**
 * @author tech@intellij.io
 */
public class SocketChannelFactory implements ChannelFactory {
    @Override
    public Channel getChannel() {
        return new SocketChannel();
    }
}
