package dp03_singleton;

/**
 * @author winterfell
 */
public class Singleton2 {

    private Singleton2() {
    }

    private static class Singleton2Inner {
        static Singleton2 instance = new Singleton2();
    }

    public Singleton2 getInstance() {
        return Singleton2Inner.instance;
    }

}
