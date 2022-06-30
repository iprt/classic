package dp01_observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * Observer 为观察者
 *
 * @author winterfell
 */
public class JdkObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("被观察者自身 o ：" + o);

        System.out.println("被观察者主动传递的值 ：" + arg);

    }
}
