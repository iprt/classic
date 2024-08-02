package dp01_observer.basic;

import java.util.HashSet;
import java.util.Set;

/**
 * 主题的实现
 *
 * @author tech@intellij.io
 */
public class SubjectImpl implements Subject {

    /**
     * 观察者的集合，由被观察者持有
     */
    private final Set<Observer> observers;

    /**
     * 消息，消息一旦更新 需要通知给观察者
     */
    private String msg;

    public SubjectImpl() {
        this.observers = new HashSet<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void publish(String msg) {
        // 赋值
        this.msg = msg;

        notifyAllObserver();

    }

    /**
     * Notify all observer.
     */
    private void notifyAllObserver() {
        observers.forEach(observer -> observer.update(msg));
    }

}
