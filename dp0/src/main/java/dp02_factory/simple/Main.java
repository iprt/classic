package dp02_factory.simple;

/**
 * @author winterfell
 */
public class Main {

    public static void main(String[] args) {

        NioServerSocketChannel nioServerSocketChannel = new ChannelFactory(NioServerSocketChannel.class).getChannel();

        System.out.println(nioServerSocketChannel);

        NioSocketChannel nioSocketChannel = new ChannelFactory(NioSocketChannel.class).getChannel();

        System.out.println(nioSocketChannel);

    }

}
