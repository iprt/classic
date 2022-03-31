package dp01_observer.basic;

/**
 * @author winterfell
 */
public class ObserverImpl1 implements Observer {

    @Override
    public void update(String msg) {

        System.out.println("观察者1得到主题发布的消息" + msg);

    }
}
