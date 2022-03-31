package dp01_observer.basic;

/**
 * 主题，同样也是被观察者
 *
 * @author winterfell
 */
public interface Subject {

    /**
     * 新增观察者
     *
     * @param observer the observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer the observer
     */
    void removeObserver(Observer observer);


    /**
     * 发布内容，在观察者模式下，被观察者一旦有什么动静，观察者对应做出一定的反应
     *
     * @param msg the msg
     */
    void publish(String msg);

}
