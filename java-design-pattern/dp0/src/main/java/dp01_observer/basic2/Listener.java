package dp01_observer.basic2;

/**
 * 持有 被观察者对象
 *
 * @author winterfell
 */
public interface Listener {

    /**
     * 持有的不是具体的内容，而是被观察者对象的引用
     *
     * @param subject the subject
     */
    void update(Subject subject);

}
