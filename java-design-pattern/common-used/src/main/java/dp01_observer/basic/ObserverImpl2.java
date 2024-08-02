package dp01_observer.basic;

/**
 * @author tech@intellij.io
 */
public class ObserverImpl2 implements Observer {

    @Override
    public void update(String msg) {

        System.out.println("观察者2得到主题发布的消息" + msg);
    }
}