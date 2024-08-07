package dp02_factory.factorymethod;

/**
 * @author tech@intellij.io
 */
public class ServerSocketChannelFactory implements ChannelFactory {
    @Override
    public Channel getChannel() {
        return new ServerSocketChannel();
    }
}
