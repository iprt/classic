package dp10_state;

/**
 * @author tech@intellij.io
 */
public interface State {

    /**
     * 上下文包含状态，根据不同的状态实现不同的操作
     *
     * @param context
     */
    void doAction(Context context);
}
