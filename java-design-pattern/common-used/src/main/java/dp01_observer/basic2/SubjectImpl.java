package dp01_observer.basic2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tech@intellij.io
 */
public class SubjectImpl implements Subject {

    private final List<Listener> listeners;

    private Status status;

    public SubjectImpl() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyAllListener() {
        for (Listener listener : listeners) {
            listener.update(this);
        }
    }

    /**
     * 发布状态并通知
     *
     * @param status
     */
    @Override
    public void publishStatus(Status status) {
        this.status = status;
        this.notifyAllListener();
    }

    @Override
    public Status status() {
        return this.status;
    }

}
