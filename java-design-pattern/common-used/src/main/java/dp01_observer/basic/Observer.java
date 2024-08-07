package dp01_observer.basic;

/**
 * @author tech@intellij.io
 */
public interface Observer {

    /**
     * 当主题更新的时候，通过msg传递消息
     *
     * @param msg the msg
     */
    void update(String msg);

}
