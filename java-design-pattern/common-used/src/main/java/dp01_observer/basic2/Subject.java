package dp01_observer.basic2;

/**
 * @author tech@intellij.io
 */
public interface Subject {

    /**
     * Add listener.
     *
     * @param listener the listener
     */
    void addListener(Listener listener);

    /**
     * Remove listener.
     *
     * @param listener the listener
     */
    void removeListener(Listener listener);

    /**
     * Notify all listener.
     */
    void notifyAllListener();

    /**
     * Publish status.
     *
     * @param status the status
     */
    void publishStatus(Status status);

    /**
     * Status status.
     *
     * @return the status
     */
    Status status();

}
