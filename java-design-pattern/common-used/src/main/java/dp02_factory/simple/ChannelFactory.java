package dp02_factory.simple;

/**
 * @author tech@intellij.io
 */
public class ChannelFactory {

    private Class clazz;

    public ChannelFactory(Class clazz) {
        this.clazz = clazz;
    }

    public <T> T getChannel() {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
