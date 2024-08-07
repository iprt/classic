package org.iproute.creating.singleton;

/**
 * 双重检查索的单例模式
 *
 * @author tech@intellij.io
 */
/**
 * @author tech@intellij.io
 */
public class Singleton4 {

    private static Singleton4 instance = null;

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        if (null == instance) {
            Singleton4 sc;
            synchronized (Singleton4.class) {
                sc = instance;
                synchronized (Singleton4.class) {
                    if (null == sc) {
                        sc = new Singleton4();
                    }
                }
                instance = sc;
            }
        }
        return instance;
    }

}
