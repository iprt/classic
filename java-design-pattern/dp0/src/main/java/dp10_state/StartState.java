package dp10_state;

/**
 * @author winterfell
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("this is start state...");
    }
}
