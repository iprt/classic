package dp03_singleton;

/**
 * double check 的单例
 * 保证了多线程状态的下的单例安全
 *
 * @author winterfell
 */
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        // 双重检查锁
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
