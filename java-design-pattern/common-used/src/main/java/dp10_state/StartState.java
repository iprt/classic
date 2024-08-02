package dp10_state;

/**
 * @author tech@intellij.io
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("this is start state...");
    }
}
