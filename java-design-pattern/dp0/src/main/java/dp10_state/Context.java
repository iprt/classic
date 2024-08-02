package dp10_state;

/**
 * 包含了状态
 *
 * @author tech@intellij.io
 */
public class Context {

    private State state;

    public Context() {
        this.state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
